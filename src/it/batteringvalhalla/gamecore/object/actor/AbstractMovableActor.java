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
			if (Math.abs(speedY - 8f) <= maxSpeed)
				speedY -= 8f;

			break;
		case sud:
			if (Math.abs(speedY + 8f) <= maxSpeed)
				speedY += 8f;

			break;
		case est:
			if (Math.abs(speedX + 8f) <= maxSpeed)
				speedX += 8f;

			break;
		case ovest:
			if (Math.abs(speedX - 8f) <= maxSpeed)
				speedX -= 8f;

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
	public void postCollision(AbstractGameObject obj) {
		super.postCollision(obj);

		double distobj = Math.sqrt(Math.pow(
				Math.abs(((Actor) obj).getSpeedX() - obj.getX()), 2.0)
				+ Math.pow(Math.abs(((Actor) obj).getSpeedY() - obj.getY()),
						2.0));
		double dist = Math.sqrt(Math.pow(Math.abs(this.speedX - this.x), 2.0)
				+ Math.pow(Math.abs(this.speedY - this.y), 2.0));

		((Actor) obj).setSpeedX(((Actor) obj).getSpeedX() + this.speedX);
		((Actor) obj).setSpeedY(((Actor) obj).getSpeedY() + this.speedY);

		if (dist > distobj) {
			this.speedX += -1 * (this.speedX / 0.5);
			this.speedY += -1 * (this.speedY / 0.5);
		} else if (dist == distobj) {
			this.speedX += -1 * (this.speedX / 0.25);
			this.speedY += -1 * (this.speedY / 0.25);
		}
	}
}
