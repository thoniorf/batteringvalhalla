package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamecore.object.AbstractGameObject;

public abstract class AbstractMovableActor extends AbstractGameObject implements
		MovableActor {

	private Direction direction = Direction.stop;

	public AbstractMovableActor(int x, int y) {
		super(x, y);

	}

	protected float maxSpeed;

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	private int score;

	public void setScore(int value) {
		this.score = value;
	}

	public int getScore() {
		return this.score;
	}

	protected float speedX;

	public void setSpeedX(float value) {
		this.speedX = value;
	}

	public float getSpeedX() {
		return this.speedX;
	}

	protected float speedY;

	public void setSpeedY(float value) {
		this.speedY = value;
	}

	public float getSpeedY() {
		return this.speedY;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
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
		collider.setLocation(this.x, this.y);
		collider.downgradeCollisionpoint();

	}

	public void move() {
		x += (int) speedX;
		y += (int) speedY;
	}

	@Override
	public void postCollision(Actor act) {
		float speedPx = 0.0f, speedPy = 0.0f;
		float speedAx = 0.0f, speedAy = 0.0f;

		speedPx = this.speedX;
		speedPy = this.speedY;
		speedAx = act.getSpeedX();
		speedAy = act.getSpeedY();

		if (this.speedX == 0 && this.speedY == 0) {
			this.speedX = speedAx;
			this.speedY = speedAy;
			act.setSpeedX(-1 * act.getSpeedX());
			act.setSpeedY(-1 * act.getSpeedY());
		} else if (act.speedX == 0 && act.speedY == 0) {
			act.setSpeedX(speedPx);
			act.setSpeedY(speedPy);
			this.speedX = -1 * this.speedX;
			this.speedY = -1 * this.speedY;
		} else {
			this.speedX = -1 * this.speedX;
			this.speedY = -1 * this.speedY;
			act.setSpeedX(-1 * act.getSpeedX());
			act.setSpeedY(-1 * act.getSpeedY());
		}

	}
}
