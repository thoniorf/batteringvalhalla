package it.batteringvalhalla.gamecore.object.actor;

public class Player extends Actor {
	public static String username;
	public static Integer score = 0;

	public Player(int x, int y, Direction direction) {
		super(x, y);
		this.collider.setBounds(this.x, this.y, width, height);
	}
}
