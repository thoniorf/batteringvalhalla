package Actor;

import Movable.AbstractMovableActor;
import Movable.Direction;

public abstract class Actor extends AbstractMovableActor {

	// width
	// height in base all'immagine

	public Actor(int x, int y, Direction direction) {
		super(x, y, Direction.stop);
		// speed
		this.collider.setBounds(this.x, this.y, width, height);// da modificare
																// in base
																// all'immagine

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

}
