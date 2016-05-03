package it.batteringvalhalla.gamecore.network;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamecore.object.Entity;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;

public class Server implements Runnable {
	protected int maxClients = 2;
	protected ServerSocket socket;
	protected ServerStatus status;
	protected List<ServerDeamon> clients = new ArrayList<>();

	public Server(ServerSocket socket) {
		this.socket = socket;
		this.status = ServerStatus.WAITING;
	}

	public void warmUpLevel() {
		// set level vars
		GameWorld.setMax_enemy(this.maxClients);
		GameWorld.setEnemies(this.maxClients);
		GameWorld.setObjects(new CopyOnWriteArrayList<>());
		for (ServerDeamon serverDeamon : this.clients) {
			GameWorld.getObjects().add(serverDeamon.client);
		}
		GameWorld.getObjects().addAll(GameWorld.getWalls());
	}

	@Override
	public void run() {
		this.syncAll();
		this.warmUpLevel();
		this.status = ServerStatus.RUNNING;
		while (!ServerStatus.STOP.equals(this.status)) {
			while (ServerStatus.RUNNING.equals(this.status)) {
				for (ServerDeamon serverDeamon : this.clients) {
					for (Entity entity : GameWorld.getObjects()) {
						if ((entity instanceof OnlineCharacter) && (((OnlineCharacter) entity)
								.getOnline_user() == serverDeamon.client.getOnline_user())) {
							entity = serverDeamon.client;
						}
					}
				}
				GameWorld.update();
				CollisionHandler.setObjects(GameWorld.getObjects());
				CollisionHandler.check();
				for (ServerDeamon deamon : this.clients) {
					for (ServerDeamon minion : this.clients) {
						deamon.send(minion.client);
					}
				}
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		if (ServerStatus.EMPTY.equals(this.status)) {
			return;
		}
	}

	public void syncAll() {
		for (ServerDeamon deamon : this.clients) {
			for (ServerDeamon minion : this.clients) {
				if (deamon.id != minion.id) {
					deamon.sendOther(minion);
				}
			}
			new Thread(deamon).start();
		}
	}

	public boolean addClient(ServerDeamon deamon) {
		if (!ServerStatus.FULL.equals(this.status)) {
			// add to clients lists
			this.clients.add(deamon);
			// sync client object
			deamon.syncClient();
			if (this.clients.size() == this.maxClients) {
				new Thread(this).start();
			}
			return true;
		}

		return false;
	}

	public void removeClient(ServerDeamon clientDeamon) {
		this.clients.remove(clientDeamon);
		if (this.clients.isEmpty()) {
			this.status = ServerStatus.EMPTY;
		}
	}

	public ServerStatus getStatus() {
		return this.status;
	}

	public void setStatus(ServerStatus status) {
		this.status = status;
	}

}
