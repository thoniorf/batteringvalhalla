package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.collision.CollisionHandler;

public class Server implements Runnable {
    public static int maxClients = 2;
    public static ServerStatus status = ServerStatus.WAITING;
    protected ServerSocket socket;
    protected List<ServerDeamon> clients;

    public Server(ServerSocket socket) {
	this.socket = socket;
	clients = new ArrayList<>();
	status = ServerStatus.WAITING;
    }

    public void addClient(ServerDeamon deamon) {
	// add to clients lists
	this.clients.add(deamon);
	// sync client object
	deamon.syncClient();
	for (ServerDeamon other : clients) {
	    if (other.id != deamon.id) {
		other.send(deamon.client);
		deamon.send(other.client);
	    }
	}
    }

    public void closeAll() {
	for (ServerDeamon deamon : clients) {
	    deamon.protocol.close(deamon.socket);
	}
    }

    public void removeClient(ServerDeamon clientDeamon) {
	this.clients.remove(clientDeamon);
	if (this.clients.isEmpty()) {
	    status = ServerStatus.STOP;
	}
    }

    @Override
    public void run() {
	this.startAll();
	this.warmUpLevel();
	Server.status = ServerStatus.RUNNING;
	while (!ServerStatus.STOP.equals(Server.status)) {
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

		    serverDeamon.send(CollisionHandler.isCollisioned());

		}

	    }
	}
	synchronized (clients) {
	    for (ServerDeamon serverDeamon : clients) {
		serverDeamon.protocol.close(serverDeamon.socket);
		System.out.println(serverDeamon.client.getOnline_user() + " can't play anymore");
	    }
	}

	try {
	    socket.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void startAll() {
	for (ServerDeamon deamon : this.clients) {
	    new Thread(deamon).start();
	}
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
}
