package it.batteringvalhalla.gamecore.ia;

import java.util.Random;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.actor.AbstractActor;
import it.batteringvalhalla.gamecore.object.actor.Enemy;
import it.batteringvalhalla.gamecore.object.direction.Direction;

public class AbstractIA {

	Arena arena;
	Enemy npc;
	Long startime;
	Long currentime;

	public Enemy getEnemy() {
		return npc;
	}

	public AbstractIA(Enemy enemy, Arena arena) {
		this.npc = enemy;
		this.arena = arena;
		startime = System.currentTimeMillis();
	}

	public void update() {
		currentime = System.currentTimeMillis();
		int dir = 5;

		if (currentime - startime <= 5000) {
			startime = currentime;
			dir = new Random().nextInt(6) + 1;
			if (!rightDir(dir)) {
				dir = 5;
			}
		}

		npc.setMoveDirection(Direction.fromInt(dir));
	}

	protected boolean canMove(float x, float y) {
		if (arena.getShape().contains(x, y))
			return true;
		return false;
	}

	protected boolean rightDir(int dir) {
		switch (dir) {
		case 1:
			return canMove(npc.getOrigin().x, npc.getOrigin().y - AbstractActor.getMaxVelocity());
		case 2:
			return canMove(npc.getOrigin().x, npc.getOrigin().y + AbstractActor.getMaxVelocity());
		case 3:
			return canMove(npc.getOrigin().x + AbstractActor.getMaxVelocity(), npc.getOrigin().y);
		case 4:
			return canMove(npc.getOrigin().x - AbstractActor.getMaxVelocity(), npc.getOrigin().y);
		default:
			break;
		}
		return false;
	}

	public void levelUp(int level) {

	}

}
