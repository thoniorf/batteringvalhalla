package it.batteringvalhalla.gamecore.network;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.State;
import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamecore.object.Entity;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
				CollisionHandler.setObjects(GameWorld.getObjects());
				CollisionHandler.check();
				String leave = leaveClient(GameWorld.getObjects());
				if (leave != null) {
					System.out.println(leave + " ha perso");
				}
				boolean won = whoWon(GameWorld.getObjects());
				if (won) {
					this.status = ServerStatus.STOP;
					System.out.println(won);
				}

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
		return true;
	}

	public void removeClient(ServerDeamon clientDeamon) {
		this.clients.remove(clientDeamon);
		if (this.clients.isEmpty()) {
			this.status = ServerStatus.EMPTY;
		}
	}

	public String leaveClient(List<Entity> s) {
		String name = null;
		for (int i = 0; i < s.size(); i++) {
			if (s.get(i) instanceof OnlineCharacter && ((OnlineCharacter) s.get(i)).getState().equals(State.Over)) {
				name = ((OnlineCharacter) s.get(i)).getOnline_user();
				s.remove(i);
				return name;
			}
		}
		return name;
	}

	public boolean whoWon(List<Entity> s) {
		int count = 0;
		for (int i = 0; i < s.size(); i++) {
			if (s.get(i) instanceof OnlineCharacter) {
				count++;
			}

		}
		if (count == 1) {
			return true;
		}
		return false;
	}

	public ServerStatus getStatus() {
		return this.status;
	}

	public void setStatus(ServerStatus status) {
		this.status = status;
	}

}
