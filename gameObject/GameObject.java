package gameObject;

import Collision.CollisionShape;

public interface GameObject {
	public CollisionShape getCollitionShape();

	public int getX();

	public int getY();

	public int getHeight();

	public int getWidth();

	public void paint();

	public void postCollition();

}
