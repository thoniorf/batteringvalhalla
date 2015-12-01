package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamegui.editorActor.ImageEditor;

public class Player extends Actor {
	private static String username;
	public static Integer score = 0;

	public Player(int x, int y) {
		super(x, y, ImageEditor.getIndexTesta(), ImageEditor.getIndexBusto(), ImageEditor.getIndexCapra());
		setDirection(Direction.est);
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
