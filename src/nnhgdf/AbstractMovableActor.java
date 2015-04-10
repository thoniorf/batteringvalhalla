package nnhgdf;
public abstract class AbstractMovableActor extends AbstractGameObject implements
		MovableActor {
	private int live;

	public void setLive(int value) {
		this.live = value;
	}

	public int getLive() {
		return this.live;
	}

	private int score;

	public void setScore(int value) {
		this.score = value;
	}

	public int getScore() {
		return this.score;
	}

	private int speedX;

	public void setSpeedX(int value) {
		this.speedX = value;
	}

	public int getSpeedX() {
		return this.speedX;
	}

	private int speedY;

	public void setSpeedY(int value) {
		this.speedY = value;
	}

	public int getSpeedY() {
		return this.speedY;
	}
@Override
public Direction getDirection() {
		return null;
	}

	@Override
	public void setDirection(Direction direction) {
	}
}
