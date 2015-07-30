package it.batteringvalhalla.gamecore.object.actor;

public class Player extends Actor {
	public static String username;

	public Player(int x, int y, Direction direction) {
		super(x, y);
		// speed
		this.collider.setBounds(this.x, this.y, width, height);
	}
}
