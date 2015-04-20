package it.batteringvalhalla.gamecore.object;

import it.thoniorf.collision.collisionshape.CollisionShape;

public abstract class AbstractGameObject implements GameObject {

	protected Integer x;
	protected Integer y;
	protected Integer height;
	protected Integer width;
	protected CollisionShape collider;

	public AbstractGameObject() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setX(Integer x) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getX() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setY(Integer y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getY() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHeight(Integer height) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getHeight() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public void setWidth(Integer width) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getWidth() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public void setCollisionShape(CollisionShape collider) {
		// TODO Auto-generated method stub

	}

	@Override
	public CollisionShape getCollisionShape() {
		// TODO Auto-generated method stub
		return collider;
	}

	@Override
	public void paint() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
