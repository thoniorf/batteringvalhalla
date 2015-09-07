package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamecore.object.AbstractGameObject;

public abstract class AbstractMovableActor extends AbstractGameObject implements
		MovableActor {

	protected Direction direction;
	protected float maxSpeed;
	protected float speedX;
	protected float speedY;

	public AbstractMovableActor(int x, int y) {
		super(x, y);
		direction = Direction.stop;

	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public float getSpeedX() {
		return this.speedX;
	}

	public float getSpeedY() {
		return this.speedY;
	}

	@Override
	public void postCollision() {
		move();
	}

	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setSpeedX(float value) {
		this.speedX = value;
	}

	public void setSpeedY(float value) {
		this.speedY = value;
	}

	@Override
	public void update() {
		switch (direction) {
		case nord:
			if (Math.abs(speedY - 1f) <= maxSpeed)
				speedY -= 1f;

			break;
		case sud:
			if (Math.abs(speedY + 1f) <= maxSpeed)
				speedY += 1f;

			break;
		case est:
			if (Math.abs(speedX + 1f) <= maxSpeed)
				speedX += 1f;

			break;
		case ovest:
			if (Math.abs(speedX - 1f) <= maxSpeed)
				speedX -= 1f;

			break;
		case stop:

			if (Math.abs(speedX - 0.5) >= 0 && speedX > 0)
				speedX -= 0.5;
			else if (Math.abs(speedX + 0.5) >= 0 && speedX < 0)
				speedX += 0.5;
			if (Math.abs(speedY - 0.5) >= 0 && speedY > 0)
				speedY -= 0.5;
			else if (Math.abs(speedY + 0.5) >= 0 && speedY < 0)
				speedY += 0.5;
			break;

		default:

			break;

		}

		move();
		collider.setLocation(this.x - width / 2, this.y - height / 2);
		collider.downgradeCollisionpoint();

	}

	public void move() {
		x += (int) speedX;
		y += (int) speedY;
	}
}
