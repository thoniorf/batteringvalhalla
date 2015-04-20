package it.batteringvalhalla.gamecore.object.actor;

import it.thoniorf.collision.collisionshape.CollisionShape;

public class Actor extends AbstractMovableActor {

	public Actor(String sprite) {
		this.health = 100;
		this.sprite = sprite;
		this.x = 100;
		this.y = 100;
		this.hor = 0;
		this.ver = 0;
		this.speed = 5;
		this.collider = new CollisionShape(this.x, this.y, 20, 20);
		this.collider.setBounds(this.x, this.y, 20, 20);
	}

	public Integer getHor() {
		return hor;
	}

	public Integer getVer() {
		return ver;
	}

}
