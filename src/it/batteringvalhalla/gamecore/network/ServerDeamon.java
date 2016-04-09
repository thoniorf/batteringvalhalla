package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.Socket;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.object.actor.Actor;

public class ServerDeamon implements Runnable {
	protected String username;
	protected Actor actor;
	protected Socket socket;
	protected Server server;
	protected NetworkProtocol protocol;

	public ServerDeamon(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		try {
			protocol = new NetworkProtocol(socket.getInputStream(), socket.getOutputStream());
			socket.setSoTimeout(60000);
		} catch (IOException e) {
			System.err.println("Error with client connection I/O during initialization");
			e.printStackTrace();
		}
	}

	public void requestClientObjects() {
		// send and receive gameworld objects
		try {
			username = (String) protocol.request();
			protocol.send(GameWorld.getArena());
		} catch (IOException e) {
			System.err.println("Error with server I/O during sync");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found during sync in server");
		}
	}

	public void close() {
		try {
			protocol.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Error with server I/O on close");
		}

	}

	@Override
	public void run() {
		// try {
		// while (protocol.isAvaible()) {
		// // update the client player
		// protocol.request();
		// }
		// } catch (ClassNotFoundException e) {
		// System.err.println("Class not found on update");
		// return;
		// } catch (IOException e) {
		// System.err.println("Error with client connection I/O on update");
		// return;
		// }
		// server.removeClient(this);

	}

	public String getUser() {
		return username;
	}
}
