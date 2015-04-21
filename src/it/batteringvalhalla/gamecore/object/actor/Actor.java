package Actor;

import java.awt.Color;
import java.awt.Graphics;

import Movable.AbstractMovableActor;
import Movable.Direction;

public abstract class Actor extends AbstractMovableActor {

	public static final int width2 = 30;
	public static final int height2 = 50;

	public Actor(int x, int y, Direction direction) {
		super(x, y, Direction.stop);
		this.setHeight(30);
		this.setWidth(90);
		this.collider.setBounds(this.x, this.y, width2, height2);

	}

	private int score;

	@Override
	public void setScore(int value) {
		this.score = value;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	private int live;

	public void setLive(int value) {
		this.live = value;
	}

	public int getLive() {
		return this.live;
	}

	public void incrementLive() {
		if (live == 3) {

			return;
		}

		live += 1;
	}

	public void incrementScore() {
		score += 10;
	}

	public void decrementLive() {
		live -= 1;
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		g.setColor(Color.blue);
		g.fillRect(x + 30, y - 40, width2, height2);
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);

	}

}
