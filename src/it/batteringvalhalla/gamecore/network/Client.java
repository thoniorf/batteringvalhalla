package it.batteringvalhalla.gamecore.network;

import java.awt.Point;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.State;
import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.input.PlayerControls;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.object.Entity;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;
import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.GamePanel;
import it.batteringvalhalla.gamegui.menu.WaitMenu;

public class Client implements Runnable {
    public static int port = 46505;
    protected NetworkProtocol protocol;
    protected String hostname;
    protected Socket socket;
    protected int client_id;
    protected JPanel panel;
    protected OnlineCharacter character;
    protected ArrayList<OnlineCharacter> opponents;
    protected Player player;
    protected boolean synced;

    public Client(String hostname) {
	synced = false;
	this.hostname = hostname;
	this.character = new OnlineCharacter(Player.getUsername(), new Point(0, 0), ManagerFilePlayer.getTop(),
		ManagerFilePlayer.getMid(), ManagerFilePlayer.getBot());

    }

    public boolean connect() {
	try {
	    this.socket = new Socket();
	    // this.socket.setSoTimeout(3000);
	    this.socket.connect(new InetSocketAddress(this.hostname, port), 3000);
	    this.protocol = new NetworkProtocol(this.socket.getInputStream(), this.socket.getOutputStream());
	    // this.socket.setSoTimeout(0);
	} catch (UnknownHostException e) {
	    System.err.println("Host is unreacheable");
	    GameFrame.instance().showOnlineError("unknownhost");
	    return false;
	} catch (SocketTimeoutException e) {
	    System.err.println("Connection timeout. Maybe the server is full");
	    GameFrame.instance().showOnlineError("serverfull");
	    return false;
	} catch (IOException e) {
	    System.err.println("Error with server connection I/O on initialization");
	    GameFrame.instance().showOnlineError("disconnected");
	    return false;
	}
	return true;

    }

    public void getInput() throws IOException {
	if (PlayerControls.getKeys().get("W")[0] == 1) {
	    this.protocol.send(Direction.nord);
	}
	if (PlayerControls.getKeys().get("A")[0] == 1) {
	    this.protocol.send(Direction.ovest);
	}
	if (PlayerControls.getKeys().get("S")[0] == 1) {
	    this.protocol.send(Direction.sud);
	}
	if (PlayerControls.getKeys().get("D")[0] == 1) {
	    this.protocol.send(Direction.est);
	}
	if (PlayerControls.getKeys().get("SPACE")[0] == 1) {
	    this.character.charge();
	}
    }

    @Override
    public void run() {
	if (!connect()) {
	    return;
	}
	sync();
	warmUpLevel();
	GameFrame.instance().startClient(this);
	while (!this.socket.isClosed() && !ServerStatus.STOP.equals(Server.status)) {

	    try {
		this.getInput();
		CharacterMessage message = (CharacterMessage) this.protocol.request();
		if (!message.syncCharacter(character)) {
		    for (int i = 0; i < opponents.size(); i++) {
			if (message.syncCharacter(opponents.get(i))) {
			    break;
			}
		    }
		}

		GameWorld.update();
		if (State.Over.equals(GameManager.getState())) {
		    ((GamePanel) panel).gameOver();
		}
		if (whoWon(GameWorld.getObjects())) {
		    Server.status = ServerStatus.STOP;
		    protocol.close(socket);
		    panel.repaint();
		    try {
			Thread.sleep(3000);
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    GameFrame.instance().restart();
		    return;
		}
		panel.repaint();
	    } catch (IOException e) {
		System.err.println("Error with server connection I/O on update");
		GameFrame.instance().showOnlineError("disconnected");
		protocol.close(socket);
		return;

	    } catch (ClassNotFoundException e) {
		System.err.println("Class not found");
		GameFrame.instance().showOnlineError("disconnected");
		protocol.close(socket);
		return;
	    }
	}
    }

    public void setPanel(JPanel panel) {
	this.panel = panel;
    }

    public void sync() {
	try {
	    this.syncLevel();
	    this.syncCharacter();
	    this.syncOpponents();
	} catch (IOException e) {
	    System.err.println("Error with server I/O during sync");
	    GameFrame.instance().showOnlineError("disconnected");
	    this.protocol.close(this.socket);
	} catch (ClassNotFoundException e) {
	    System.err.println("Class not found during sync in client");
	    GameFrame.instance().showOnlineError("disconnected");
	    this.protocol.close(this.socket);
	}
    }

    public void syncCharacter() throws IOException, ClassNotFoundException {
	// send character
	this.client_id = (int) this.protocol.request();
	// set character spawn point
	this.character.getOrigin().move(GameWorld.getArena().getSpawn().get(this.client_id - 1).x,
		GameWorld.getArena().getSpawn().get(this.client_id - 1).y);
	this.protocol.send(this.character);
	WaitMenu.setPlayer(character.getOnline_user());
	WaitMenu.lobby.repaint();
    }

    public void syncLevel() throws ClassNotFoundException, IOException {
	// receive arena
	GameWorld.setArena((Arena) this.protocol.request());
	// receive walls
	int wallsize = (int) this.protocol.request();
	GameWorld.setWalls(new ArrayList<VerySquareWall>());
	for (int i = 0; i < wallsize; i++) {
	    GameWorld.getWalls().add((VerySquareWall) protocol.request());
	}
    }

    public void syncOpponents() throws ClassNotFoundException, IOException {
	this.opponents = new ArrayList<OnlineCharacter>();
	int maxOpponents = (int) this.protocol.request();
	// sync other players
	for (int i = 1; i < maxOpponents; i++) {
	    OnlineCharacter opponent = (OnlineCharacter) this.protocol.request();
	    this.opponents.add(opponent);
	    WaitMenu.setPlayer(opponent.getOnline_user());
	    WaitMenu.lobby.repaint();
	}
    }

    public void warmUpLevel() {
	// set level vars
	GameWorld.setPlayer(character);
	GameWorld.setMax_enemy(this.opponents.size());
	GameWorld.setEnemies(this.opponents.size());
	GameWorld.setObjects(new ArrayList<Entity>());
	GameWorld.getObjects().add(this.character);
	GameWorld.getObjects().addAll(this.opponents);
	GameWorld.getObjects().addAll(GameWorld.getWalls());
    }

    public boolean whoWon(List<Entity> s) {
	int alive = 0;
	String winner = "";
	for (int i = 0; i < GameWorld.getObjects().size(); i++) {
	    if (GameWorld.getObjects().get(i) instanceof OnlineCharacter) {
		OnlineCharacter couldWin = (OnlineCharacter) GameWorld.getObjects().get(i);
		if (GameWorld.getObjects().get(i).getAlive()) {
		    alive++;
		    winner = couldWin.getOnline_user();
		}
	    }
	}
	if (alive > 1) {
	    return false;
	}
	((GamePanel) panel).winner(winner);
	return true;
    }

}
