package it.batteringvalhalla.gamecore.object;

import it.batteringvalhalla.gamecore.collision.shape.CollisionShape;

import java.awt.Graphics;

public abstract class AbstractGameObject {

	protected int x;
	protected int y;
	protected int height;

	protected int width;
	protected CollisionShape collider;

	public AbstractGameObject(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public void setCollider(CollisionShape value) {
		this.collider = value;
	}

	public CollisionShape getCollitionShape() {
		return collider;
	}

	public void paint(Graphics g) {

	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void update() {

	}

	public void postCollition() {

	}
}
