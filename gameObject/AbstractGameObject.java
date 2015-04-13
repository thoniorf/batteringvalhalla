package gameObject;

import Collision.CollisionShape;

public abstract class AbstractGameObject implements GameObject {

	protected int x;
	protected int y;
	protected int height;

	protected int width;
	protected CollisionShape collider;

	public AbstractGameObject(int x, int y) {

		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	public void setCollider(CollisionShape value) {
		this.collider = value;
	}

	@Override
	public CollisionShape getCollitionShape() {
		return collider;
	}

	@Override
	public void paint() {
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
