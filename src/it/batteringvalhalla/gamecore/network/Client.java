package it.batteringvalhalla.gamecore.network;

import java.awt.Point;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.input.PlayerControls;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.object.Entity;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;
import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;

public class Client implements Runnable {
	private static final int port = 46505;
	protected NetworkProtocol protocol;
	protected String hostname;
	protected Socket socket;
	protected int client_id;
	protected JPanel panel;
	protected OnlineCharacter character;
	protected ArrayList<OnlineCharacter> opponents;

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
			this.socket.setSoTimeout(0);
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

	public void syncLevel() throws ClassNotFoundException, IOException {
		// receive arena
		GameWorld.setArena((Arena) this.protocol.request());
		// receive walls
		int wallsize = (int) this.protocol.request();
		GameWorld.setWalls(new ArrayList<VerySquareWall>());
		for (int i = 0; i < wallsize; i++) {
			Object req = this.protocol.request();
			GameWorld.getWalls().add(new VerySquareWall(((Entity) req).getOrigin().x, ((Entity) req).getOrigin().y,
					((VerySquareWall) req).getLife()));
		}
	}

	public void syncCharacter() throws IOException, ClassNotFoundException {
		// send character
		client_id = (int) this.protocol.request();
		// set character spawn point
		character.getOrigin().move(GameWorld.getArena().getSpawn().get(client_id - 1).x,
				GameWorld.getArena().getSpawn().get(client_id - 1).y);
		this.protocol.send(character);

	}

	public void syncOpponents() throws ClassNotFoundException, IOException {
		opponents = new ArrayList<OnlineCharacter>();
		// sync other players
		for (int i = 1; i < 2; i++) {
			opponents.add(new OnlineCharacter((OnlineCharacter) protocol.request()));
		}
	}

	public void warmUpLevel() {
		// set level vars
		GameWorld.setMax_enemy(opponents.size());
		GameWorld.setEnemies(opponents.size());
		GameWorld.setObjects(new CopyOnWriteArrayList<>());
		GameWorld.getObjects().add(character);
		GameWorld.getObjects().addAll(opponents);
	}

	public void sync() {
		try {
			syncLevel();
			syncCharacter();
			syncOpponents();
		} catch (IOException e) {
			System.err.println("Error with server I/O during sync");
			this.protocol.close(socket);
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found during sync in client");
			this.protocol.close(socket);
		}
	}

	@Override
	public void run() {
		warmUpLevel();
		System.out.println(client_id + " is ready");
		while (!this.socket.isClosed()) {
			try {
				getInput();
				OnlineCharacter request = (OnlineCharacter) protocol.request();
				for (Entity opponent : GameWorld.getObjects()) {
					if (opponent instanceof OnlineCharacter
							&& request.getOnline_user().equals(((OnlineCharacter) opponent).getOnline_user())) {
						((OnlineCharacter) opponent).setMoveDirection(request.getMoveDirection());
					}
				}
				GameWorld.update();
				panel.repaint();
			} catch (IOException e) {
				System.err.println("Error with server connection I/O on update");
				return;
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found");
			}
		}
		System.out.println("End");
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public OnlineCharacter getCharacter() {
		return character;
	}

	public void setCharacter(OnlineCharacter character) {
		this.character = character;
	}

	public void getInput() throws IOException {
		protocol.send(Direction.stop);
		character.setMoveDirection(Direction.stop);
		if (PlayerControls.getKeys().get("W")[0] == 1) {
			protocol.send(Direction.nord);
			character.setMoveDirection(Direction.nord);
		}
		if (PlayerControls.getKeys().get("A")[0] == 1) {
			protocol.send(Direction.ovest);
			character.setMoveDirection(Direction.ovest);
		}
		if (PlayerControls.getKeys().get("S")[0] == 1) {
			protocol.send(Direction.sud);
			character.setMoveDirection(Direction.sud);
		}
		if (PlayerControls.getKeys().get("D")[0] == 1) {
			protocol.send(Direction.est);
			character.setMoveDirection(Direction.est);
		}
		if (PlayerControls.getKeys().get("SPACE")[0] == 1) {
			character.charge();
		}
	}
}
