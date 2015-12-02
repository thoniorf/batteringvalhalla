package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamecore.object.AbstractGameObject;

public abstract class AbstractMovableActor extends AbstractGameObject implements MovableActor {

	protected Direction direction;
	protected float maxSpeed = 4f;
	protected float chargeSpeed = 10f;
	protected float speedX;
	protected float speedY;
	protected boolean charge;
	protected long last_charge;
	protected long current_time;
	protected long diff_time = 0;

	public AbstractMovableActor(int x, int y) {
		super(x, y);
		direction = Direction.stop;
		charge = false;
		last_charge = 0;

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
		if (value > maxSpeed)
			this.speedX = maxSpeed;
		this.speedX = value;
	}

	public void setSpeedY(float value) {
		if (value > maxSpeed)
			this.speedY = maxSpeed;
		this.speedY = value;
	}

	public boolean getCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}

	@Override
	public void update() {
		switch (direction) {
		case nord:
			if (Math.abs(speedY - 1f) <= maxSpeed)
				speedY -= 1f;
			if (charge) {
				speedY = chargeSpeed * -1;
			}

			break;
		case sud:
			if (Math.abs(speedY + 1f) <= maxSpeed)
				speedY += 1f;
			if (charge) {
				speedY = chargeSpeed;
			}
			break;
		case est:
			if (Math.abs(speedX + 1f) <= maxSpeed)
				speedX += 1f;
			if (charge) {
				speedX = chargeSpeed;

			}
			break;
		case ovest:
			if (Math.abs(speedX - 1f) <= maxSpeed)
				speedX -= 1f;
			if (charge) {
				speedX = chargeSpeed * -1;

			}
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
		charge = false;
	}

	public boolean tryCharge() {
		current_time = System.currentTimeMillis();
		diff_time = current_time - last_charge;
		if (!charge && diff_time > 5000) {
			charge = true;
			last_charge = current_time;
		}

		return charge;

	}

	public void move() {
		x += (int) speedX;
		y += (int) speedY;
	}
}
