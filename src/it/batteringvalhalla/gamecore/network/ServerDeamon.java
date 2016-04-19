package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;

public class ServerDeamon implements Runnable {
	protected int id;
	protected String username;
	protected int[] user_sprites;
	protected Socket socket;
	protected Server server;
	protected NetworkProtocol protocol;
	protected OnlineCharacter client;

	public ServerDeamon(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		try {
			this.protocol = new NetworkProtocol(socket.getInputStream(), socket.getOutputStream());
		} catch (IOException e) {
			System.err.println("Error with client connection I/O during initialization");
			e.printStackTrace();
		}
		this.user_sprites = new int[3];
	}

	public void send(Object obj) {
		try {
			this.socket.setSoTimeout(30000);
			this.protocol.send(obj);
		} catch (SocketTimeoutException e) {
			System.err.println("Timeout during sync");
			this.server.removeClient(this);
			return;
		} catch (IOException e) {
			System.err.println("Error with client I/O during sync");
			this.server.removeClient(this);
			return;
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

	public void close() {
		try {
			this.protocol.close();
			this.socket.close();
		} catch (IOException e) {
			System.err.println("Error with server I/O on close");
		}

	}

	public void sendOther(ServerDeamon minion) {
		send(minion.username);
		for (int i = 0; i < 3; i++) {
			send(minion.user_sprites[i]);
		}
		try {
			this.socket.setSoTimeout(0);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void syncClient() {
		client = (OnlineCharacter) this.request();
		// send and receive gameworld objects
		this.username = (String) this.request();
		for (int i = 0; i < 3; i++) {
			this.user_sprites[i] = (int) this.request();
		}
		// send arena
		this.send(GameWorld.getArena());
		// send walls
		this.send(GameWorld.getWalls().size());
		for (VerySquareWall wall : GameWorld.getWalls()) {
			this.send(wall);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				// update the client player
				this.protocol.request();
			} catch (SocketException e) {
				e.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found on update");
				return;
			} catch (IOException e) {
				System.err.println("Error with client connection I/O on update");
				return;
			}

		}

		this.server.removeClient(this);

	}
}
