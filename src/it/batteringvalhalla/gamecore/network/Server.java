package it.batteringvalhalla.gamecore.network;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.State;
import it.batteringvalhalla.gamecore.collision.CollisionHandler;

public class Server implements Runnable {
	public static int maxClients = 2;
	protected ServerSocket socket;
	public static ServerStatus status = ServerStatus.WAITING;
	protected List<ServerDeamon> clients;

	public Server(ServerSocket socket) {
		this.socket = socket;
		clients = new ArrayList<>();
		status = ServerStatus.WAITING;
	}

	public void warmUpLevel() {
		// set level vars
		GameWorld.setMax_enemy(maxClients);
		GameWorld.setEnemies(maxClients);
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
		status = ServerStatus.RUNNING;
		while (!ServerStatus.STOP.equals(status)) {
			while (ServerStatus.RUNNING.equals(status)) {
				CollisionHandler.setObjects(GameWorld.getObjects());
				CollisionHandler.check();
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (ServerDeamon serverDeamon : clients) {
					for (ServerDeamon minion : clients) {
						CharacterMessage message = new CharacterMessage(minion.client);
						serverDeamon.send(message);
					}
				}
				if (State.Next.equals(GameManager.getState())) {
					status = ServerStatus.STOP;
				}
			}
		}

	}

	public void syncAll() {
		for (ServerDeamon deamon : this.clients) {
			deamon.send(maxClients);
			for (ServerDeamon minion : this.clients) {
				if (deamon.id != minion.id) {
					deamon.send(minion.client);
				}
			}
			new Thread(deamon).start();
		}
	}

	public boolean addClient(ServerDeamon deamon) {
		// add to clients lists
		this.clients.add(deamon);
		// sync client object
		deamon.syncClient();
		return deamon.synced;
	}

	public void removeClient(ServerDeamon clientDeamon) {
		this.clients.remove(clientDeamon);
		if (this.clients.isEmpty()) {
			status = ServerStatus.EMPTY;
		}
	}
}
