package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.actor.player.Player;

public class Client implements Runnable {
	private static final int port = 46505;
	protected NetworkProtocol protocol;
	protected String hostname;
	protected Socket socket;
	protected Player player;

	public Client(String hostname) {
		this.hostname = hostname;
	}

	public boolean connect() {
		try {
			socket = new Socket();
			socket.setSoTimeout(500);
			socket.connect(new InetSocketAddress(hostname, port), 500);
			protocol = new NetworkProtocol(socket.getInputStream(), socket.getOutputStream());
			socket.setSoTimeout(60000);
		} catch (UnknownHostException e) {
			System.err.println("Host is unreacheable");
			return false;
		} catch (SocketTimeoutException e) {
			System.err.println("Connection timeout. Maybe the server is full");
			return false;
		} catch (IOException e) {
			System.err.println("Error with server connection I/O on initialization");
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public void sync() {
		// send and receive gameworld objects
		try {
			protocol.send(Player.getUsername());
			GameWorld.setArena((Arena) protocol.request());
			System.out.println("Arena in");
		} catch (IOException e) {
			System.err.println("Error with server I/O during sync");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found during sync in client");
		}

	}

	public void close() {
		try {
			protocol.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Error with client I/O on close");
		}

	}

	@Override
	public void run() {
		System.out.println("Ready");
		while (!socket.isClosed()) {
			try {
				protocol.send(player);
			} catch (IOException e) {
				System.err.println("Error with server connection I/O on update");
				return;
			}
		}

	}

	public void update() {
		try {
			protocol.send(player);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
