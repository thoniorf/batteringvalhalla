package it.batteringvalhalla.gamecore.collision.shape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class CollisionShape extends Rectangle {

	private Integer collisionpoint;

	public CollisionShape(Rectangle r) {
		super(r);
		inizialize();
	}

	public CollisionShape(Point p) {
		super(p);
		inizialize();
	}

	public CollisionShape(Dimension d) {
		super(d);
		inizialize();
	}

	public CollisionShape(int width, int height) {
		super(width, height);
		inizialize();
	}

	public CollisionShape(Point p, Dimension d) {
		super(p, d);
		inizialize();
	}

	public CollisionShape(int x, int y, int width, int height) {
		super(x, y, width, height);
		inizialize();
	}

	private void inizialize() {
		this.collisionpoint = 0;
	}

	public Integer getCollisionpoint() {
		return collisionpoint;
	}

	public boolean intersecable() {
		if (this.collisionpoint == 0)
			return true;

		return false;
	}

	public void updateCollisionpoint() {
		this.collisionpoint = 3;
	}

	public void downgradeCollisionpoint() {
		if (this.collisionpoint != 0) {
			this.collisionpoint -= 1;
		}
	}

	public boolean beginCollided() {
		if (this.collisionpoint != 0) {
			System.out.println("CONTACT !");
			return true;
		}

		return false;
	}

	public void paint(Graphics g) {
		g.fillRect(x, y, 40, 50);
	}
}
