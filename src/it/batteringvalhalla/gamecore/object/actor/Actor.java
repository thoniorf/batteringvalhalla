package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamecore.animation.Sprite;
import it.batteringvalhalla.gamecore.collision.shape.CollisionShape;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

import java.awt.Color;
import java.awt.Graphics;

public class Actor extends AbstractMovableActor {

	private int live;
	private Sprite sprite;

	public Actor(int x, int y) {
		super(x, y);
		this.sprite = new Sprite(ResourcesLoader.player,
				ResourcesLoader.player.getWidth(null),
				ResourcesLoader.player.getHeight(null), 90, 80, 3, 15);
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
	public void update() {
		super.update();
		sprite.update(speedX,speedY);
	};

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(sprite.getFrame(), x - width / 2, y - height / 2 - 40, null);
		g.setColor(Color.GREEN);
		g.drawRect(x - width / 2, y - height / 2, width, height);
		g.setColor(Color.RED);
		g.drawString("+", x, y);
	}

}
