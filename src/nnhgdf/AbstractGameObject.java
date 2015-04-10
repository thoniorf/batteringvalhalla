package nnhgdf;
import java.awt.geom.Rectangle2D;

public abstract class AbstractGameObject implements GameObject {
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected Rectangle2D collider;
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

	public void setCollider(Rectangle2D value) {
		this.collider = value;
	}

	public Rectangle2D getCollider() {
		return this.collider;
	}
@Override
public Rectangle2D getCollitionShape() {
		return null;
	}
@Override
public void paint() {
	}
@Override
public void update() {
	}
}
