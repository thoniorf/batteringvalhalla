package Actor;

import Movable.Direction;

public abstract class Enemy extends Actor {

	public Enemy(int x, int y, Direction direction) {
		super(x, y, Direction.stop);
		// speed
		this.collider.setBounds(this.x, this.y, width, height);
	}
}
