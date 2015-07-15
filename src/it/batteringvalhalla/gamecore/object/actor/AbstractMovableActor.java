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
		// TODO New physics
		super.postCollision(act);

		float actdx = Math.abs(act.getX() - act.getX() + act.getSpeedX());
		float actdy = Math.abs(act.getY() - act.getY() + act.getSpeedY());

		float dx = Math.abs(this.getX() - this.getX() + this.getSpeedX());
		float dy = Math.abs(this.getY() - this.getY() + this.getSpeedY());

		double distact = Math.sqrt(Math.pow(actdx, 2.0) + Math.pow(actdy, 2.0));
		double dist = Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0));

		act.setSpeedX(act.getSpeedX() + this.speedX);
		act.setSpeedY(act.getSpeedY() + this.speedY);

		if (dist > distact) {
			this.speedX += -1 * (this.speedX / 0.75);
			this.speedY += -1 * (this.speedY / 0.75);
		} else if (dist == distact) {
			this.speedX += -1 * (this.speedX / 0.5);
			this.speedY += -1 * (this.speedY / 0.5);
		}
	}
}
