package it.batteringvalhalla.gamecore.object.actor.player;

import java.awt.Point;

import it.batteringvalhalla.gamecore.object.actor.AbstractActor;

public class Player extends AbstractActor {
	protected static Integer score;
	protected static String username;

	public Player(Point origin) {
		super(origin, 0, 0, 0);
		score = new Integer(0);
	}

	public static void incScore() {
		Player.score += 1;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Player.username = username;
	}

	public static Integer getScore() {
		return score;
	}
}
