package it.batteringvalhalla.gamecore.object.actor;

import java.awt.Color;
import java.awt.Graphics;

import it.batteringvalhalla.gamecore.animation.Sprite;
import it.batteringvalhalla.gamecore.collision.shape.CollisionShape;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

public class Actor extends AbstractMovableActor {

	private int live;
	private Sprite weapon, body, mount, head;

	public Actor(int x, int y, int headId, int weapId, int mountId) {
		super(x, y);
		// TODO head load
		this.body = new Sprite(ResourcesLoader.actor_body, ResourcesLoader.actor_body.getWidth(null),
				ResourcesLoader.actor_body.getHeight(null), 115, 94, 3, 1, 0, 0, 0);
		this.mount = new Sprite(ResourcesLoader.actor_mount.get(0), ResourcesLoader.actor_mount.get(0).getWidth(null),
				ResourcesLoader.actor_mount.get(0).getHeight(null), 117, 88, 3, 16, 0, 0, 0);
		this.weapon = new Sprite(ResourcesLoader.actor_weapon.get(0),
				ResourcesLoader.actor_weapon.get(0).getWidth(null), ResourcesLoader.actor_weapon.get(0).getHeight(null),
				103, 76, 3, 16, 0, 0, 0);
		setSpeedX(0);
		setSpeedY(0);
		setMaxSpeed(5.5f);
		setHeight(30);
		setWidth(90);
		this.live = 3;
		this.collider = new CollisionShape(x - width / 2, y - height - 2, width, height);

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
		body.update(direction);
		weapon.update(direction);
		mount.update(direction);
	};

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(mount.getFrame(), x - mount.getOffsetX(), y - mount.getOffsetY(), null);
		g.drawImage(body.getFrame(), x - body.getOffsetX(), y - body.getOffsetY(), null);
		g.drawImage(weapon.getFrame(), x - weapon.getOffsetX(), y - weapon.getOffsetY(), null);
		g.setColor(Color.GREEN);
		g.drawRect(x - width / 2, y - height / 2, width, height);
		g.setColor(Color.RED);
		g.drawString("+", x, y);
	}
}
