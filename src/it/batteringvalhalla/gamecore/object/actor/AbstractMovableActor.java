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
			if (Math.abs(speedY - 16) <= maxSpeed)
				speedY -= 7.5;

			break;
		case sud:
			if (Math.abs(speedY + 16) <= maxSpeed)
				speedY += 7.5;

			break;
		case est:
			if (Math.abs(speedX + 16) <= maxSpeed)
				speedX += 7.5;

			break;
		case ovest:
			if (Math.abs(speedX - 16) <= maxSpeed)
				speedX -= 7.5;

			break;
		case stop:

			if (Math.abs(speedX - 1) >= 0 && speedX > 0)
				speedX -= 0.5;
			if (Math.abs(speedX + 1) >= 0 && speedX < 0)
				speedX += 0.5;
			if (Math.abs(speedY - 1) >= 0 && speedY > 0)
				speedY -= 0.5;
			if (Math.abs(speedY + 1) >= 0 && speedY < 0)
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
		System.out.println("Collision");
		double distobj = Math
				.sqrt(Math.pow(
						Math.abs(((Actor) obj).getSpeedX() - obj.getX()), 2)
						+ Math.pow(Math.abs(((Actor) obj).getSpeedY()
								- obj.getY()), 2));
		double dist = Math.sqrt(Math.pow(Math.abs(this.speedX - this.x), 2)
				+ Math.pow(Math.abs(this.speedY - this.y), 2));
		if (dist > distobj) {
			((Actor) obj).setSpeedX(((Actor) obj).getSpeedX() + this.speedX);
			((Actor) obj).setSpeedY(((Actor) obj).getSpeedY() + this.speedY);
			this.speedX = 0.9f;
			this.speedY = 0.9f;
		}
	}
}
