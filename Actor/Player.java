package Actor;

import Movable.Direction;

public abstract class Player extends Actor {

	public Player(int x, int y, Direction direction) {
		super(x, y, Direction.stop);
		// speed
		this.collider.setBounds(this.x, this.y, width, height);
	}
}
