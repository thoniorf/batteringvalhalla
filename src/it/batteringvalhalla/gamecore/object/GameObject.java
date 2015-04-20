package it.batteringvalhalla.gamecore.object;

import it.thoniorf.collision.collisionshape.CollisionShape;

public interface GameObject {

	public void setX(Integer x);

	public Integer getX();

	public void setY(Integer y);

	public Integer getY();

	public void setHeight(Integer height);

	public Integer getHeight();

	public void setWidth(Integer width);

	public Integer getWidth();

	public void setCollisionShape(CollisionShape collider);

	public CollisionShape getCollisionShape();

	public void paint();

	public void update();

}
