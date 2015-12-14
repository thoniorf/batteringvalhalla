package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;


public class Player extends Actor {
	private static String username;
	private static Integer score = 0;

	public Player(int x, int y) {
		super(x, y, ManagerFilePlayer.getTop(), ManagerFilePlayer.getMid(), ManagerFilePlayer.getBot());
		setDirection(Direction.stop);
		this.collider.setBounds(this.x, this.y, width, height);
	}

	public static void setName(String s) {
		username = s;
	}

	public static String getName() {
		return username;
	}

	public static Integer getScore() {
		return score;
	}

	public static void setScore(Integer points) {
		score = points;
	}
}
