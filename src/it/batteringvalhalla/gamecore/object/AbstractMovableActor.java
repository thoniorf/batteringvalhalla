package it.batteringvalhalla.gamecore.object;

import it.thoniorf.collision.collisionshape.CollisionShape;

import java.awt.Polygon;

public abstract class AbstractMovableActor extends AbstractGameObject implements
		MovableActor {

	protected Integer health;
	protected Integer x;
	protected Integer y;
	protected String sprite;
	protected Integer hor;
	protected Integer ver;
	protected Integer speed;
	protected CollisionShape collider;

	@Override
	public Integer getX() {
		return x;
	}

	@Override
	public void setX(Integer x) {
		this.x += x;

	}

	@Override
	public Integer getY() {
		return y;
	}

	@Override
	public void setY(Integer y) {
		this.y += y;
	}

	@Override
	public Integer getHealth() {
		return health;

	}

	@Override
	public void setHealth(Integer health) {
		this.health = health;
	}

	@Override
	public Integer attack() {
		return 10;
	}

	@Override
	public String getSprite() {
		return sprite;
	}

	@Override
	public void setHor(Integer hor) {
		this.hor = hor;

	}

	@Override
	public void setVer(Integer ver) {
		this.ver = ver;
	}

	@Override
	public CollisionShape getCollisionShape() {
		return collider;

	}

	@Override
	public void update(Polygon border) {
		this.collider.downgradeCollisionpoint();
		if (-1 == hor)
			this.x -= speed;
		if (1 == hor)
			this.x += speed;
		if (1 == ver)
			this.y += speed;
		if (-1 == ver)
			this.y -= speed;
		collider.setBounds(this.x, this.y, 20, 20);
		if (!border.intersects(this.x, this.y, 20, 20))
			die();

	}

	private void die() {
		health = 0;
	}
}
