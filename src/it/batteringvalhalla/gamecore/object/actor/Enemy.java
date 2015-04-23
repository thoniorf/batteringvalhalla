package it.batteringvalhalla.gamecore.object.actor;

public class Enemy extends Actor {

	public Enemy(int x, int y, Direction direction) {
		super(x, y);
		// speed
		this.collider.setBounds(this.x, this.y, width, height);
	}
}
