package it.batteringvalhalla.gamecore.object.actor.player;

import java.awt.Point;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.object.actor.AbstractActor;

public class Player extends AbstractActor {
	protected static String username;

	public Player(Point origin) {
		super(origin, ManagerFilePlayer.getTop(), ManagerFilePlayer.getMid(), ManagerFilePlayer.getBot());
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Player.username = username;
	}

}
