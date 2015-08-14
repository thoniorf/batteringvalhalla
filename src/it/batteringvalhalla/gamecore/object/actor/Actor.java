package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamecore.collision.shape.CollisionShape;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

import java.awt.Color;
import java.awt.Graphics;

public class Actor extends AbstractMovableActor {

	public static final int height2 = 50;
	public static final int width2 = 30;

	private int live;

	public Actor(int x, int y) {
		super(x, y);
		setSpeedX(0);
		setSpeedY(0);
		setMaxSpeed(5.5f);
		setHeight(30);
		setWidth(90);
		this.live = 3;
		this.collider = new CollisionShape(x, y, width, height);

	}

	public void decrementLive() {
		live -= 1;
	}

	public void setLive(Integer i) {
		this.live = i;
	}

	public int getLive() {
		return this.live;
	}

	public void incrementLive() {
		if (live == 3) {
			return;
		}
		live += 1;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(ResourcesLoader.player, x, y - 40, null);
		g.setColor(Color.blue);
		g.drawRect(x + 30, y - 40, width2, height2);
		g.setColor(Color.GREEN);
		g.drawRect(x, y, width, height);
	}

}
