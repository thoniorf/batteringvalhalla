package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamecore.collision.shape.CollisionShape;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

import java.awt.Color;
import java.awt.Graphics;

public class Actor extends AbstractMovableActor {

	private int live;

	public Actor(int x, int y) {
		super(x, y);
		setSpeedX(0);
		setSpeedY(0);
		setMaxSpeed(5.5f);
		setHeight(30);
		setWidth(90);
		this.live = 3;
		this.collider = new CollisionShape(x - width / 2, y - height - 2,
				width, height);

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
		g.drawImage(ResourcesLoader.player, x - width / 2, y - height / 2 - 40,
				null);
		g.setColor(Color.GREEN);
		g.drawRect(x - width / 2, y - height / 2, width, height);
		g.setColor(Color.RED);
		g.drawString("+", x, y);
	}

}
