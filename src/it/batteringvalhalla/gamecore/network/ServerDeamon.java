package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;
import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;

public class ServerDeamon implements Runnable {
	protected int id;
	protected Socket socket;
	protected Server server;
	protected NetworkProtocol protocol;
	protected OnlineCharacter client;

	public ServerDeamon(Socket socket, Server server, int index) {
		this.socket = socket;
		this.server = server;
		this.id = index;
		try {
			this.protocol = new NetworkProtocol(socket.getInputStream(), socket.getOutputStream());
		} catch (IOException e) {
			System.err.println("Error with client connection I/O during initialization");
			e.printStackTrace();
		}
	}

	public void send(Object obj) {
		try {
			this.protocol.send(obj);
		} catch (SocketTimeoutException e) {
			System.err.println("Timeout during sync");
			this.server.removeClient(this);
		} catch (IOException e) {
			System.err.println("Error with client I/O during sync");
			this.server.removeClient(this);
		}
	}

	public Object request() {
		try {
			return this.protocol.request();
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found during sync in server");
			this.server.removeClient(this);
		} catch (IOException e) {
			System.err.println("Error with client I/O during sync");
			this.server.removeClient(this);
		}
		return null;
	}

	public void sendOther(ServerDeamon minion) {
		send(minion.client);
	}

	public void syncLevel() {
		// send arena
		this.send(GameWorld.getArena());
		// send walls
		this.send(GameWorld.getWalls().size());
		for (VerySquareWall wall : GameWorld.getWalls()) {
			this.send(wall);
		}
	}

	public void syncCharacter() {
		// send character
		send(server.clients.size());
		client = new OnlineCharacter((OnlineCharacter) this.request());
	}

	public void syncClient() {
		syncLevel();
		syncCharacter();
	}

	@Override
	public void run() {
		while (true) {
			try {
				// update the client player
				Direction d = (Direction) this.protocol.request();
				client.setMoveDirection(d);
				// server.syncAll();
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found on update");
				break;
			} catch (IOException e) {
				System.err.println("Error with client connection I/O on update");
				break;
			}
		}
		this.server.removeClient(this);

	}
}
