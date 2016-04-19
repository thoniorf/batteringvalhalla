package it.batteringvalhalla.gamecore.network;

import java.awt.Point;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.object.actor.AbstractActor;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;
import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;

public class Client implements Runnable {
	private static final int port = 46505;
	protected NetworkProtocol protocol;
	protected String hostname;
	protected Socket socket;

	protected OnlineCharacter character;

	public Client(String hostname) {
		this.hostname = hostname;
		character = new OnlineCharacter(Player.getUsername(), new Point(0, 0), ManagerFilePlayer.getTop(),
				ManagerFilePlayer.getMid(), ManagerFilePlayer.getBot());
	}

	public boolean connect() {
		try {
			this.socket = new Socket();
			this.socket.setSoTimeout(500);
			this.socket.connect(new InetSocketAddress(this.hostname, port), 500);
			this.protocol = new NetworkProtocol(this.socket.getInputStream(), this.socket.getOutputStream());
			this.socket.setSoTimeout(30000);
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
			this.protocol.send(character);
			this.protocol.send(Player.getUsername());
			this.protocol.send(ManagerFilePlayer.getTop());
			this.protocol.send(ManagerFilePlayer.getMid());
			this.protocol.send(ManagerFilePlayer.getBot());
			GameWorld.setArena((Arena) this.protocol.request());
			ArrayList<VerySquareWall> walls = new ArrayList<VerySquareWall>();
			Integer wallsize = (Integer) this.protocol.request();
			for (int i = 0; i < wallsize; i++) {
				walls.add((VerySquareWall) this.protocol.request());
			}
			this.socket.setSoTimeout(0);
			ArrayList<AbstractActor> onlineOpponent = new ArrayList<>();
			// sync other players
			for (int i = 1; i < 2; i++) {
				String user = (String) this.protocol.request();
				int top = (int) this.protocol.request();
				int mid = (int) this.protocol.request();
				int bot = (int) this.protocol.request();
				onlineOpponent.add(new OnlineCharacter(user, new Point(0, 0), top, mid, bot));
			}
			GameWorld.setObjects(new ArrayList<>());
			GameWorld.getObjects().addAll(onlineOpponent);
			GameWorld.getObjects().addAll(walls);
			System.out.println(onlineOpponent.toString());
		} catch (IOException e) {
			System.err.println("Error with server I/O during sync");
			close();
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found during sync in client");
			close();
		}

	}

	public void close() {
		try {
			this.protocol.close();
			this.socket.close();
		} catch (IOException e) {
			System.err.println("Error with client I/O on close");
		}

	}

	@Override
	public void run() {
		System.out.println("Ready");
		while (!this.socket.isClosed()) {
			try {
				OnlineCharacter tmp = (OnlineCharacter) protocol.request();
			} catch (IOException e) {
				System.err.println("Error with server connection I/O on update");
				return;
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found");
			}
		}

	}
}
