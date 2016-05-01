package it.batteringvalhalla.gamecore.object;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

public abstract class AbstractEntity implements Entity, Comparable<Entity> {
	private static final long serialVersionUID = -7137071641988898113L;
	protected int width;
	protected int height;
	protected Boolean alive;
	protected Point origin;
	protected Shape shape;

	public AbstractEntity(Point origin) {
		this.alive = Boolean.TRUE;
		this.origin = origin;
		this.width = 50;
		this.height = 50;
		this.shape = new Rectangle(origin.x - (width / 2), origin.y - (height / 2), width, height);

	}

	@Override
	public Boolean getAlive() {
		return alive;
	}

	@Override
	public Integer getHeight() {
		return height;
	}

	@Override
	public Point getOrigin() {
		return origin;
	}

	public int getX() {
		return origin.x;
	}

	public int getY() {
		return origin.y;
	}

	@Override
	public Shape getShape() {
		return shape;
	}

	@Override
	public Integer getWidth() {

		return width;
	}

	@Override
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	@Override
	public int compareTo(Entity e) {
		if (this.getOrigin().y > e.getOrigin().y) {
			return 1;
		} else if (this.getOrigin().y < e.getOrigin().y) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public void update() {
		if (!alive) {
			return;
		}

	}
}
