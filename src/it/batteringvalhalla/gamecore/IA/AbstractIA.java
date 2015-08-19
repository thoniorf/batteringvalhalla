package it.batteringvalhalla.gamecore.IA;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.actor.Direction;
import it.batteringvalhalla.gamecore.object.actor.Enemy;

import java.util.Random;

public class AbstractIA {

	Arena arena;
	Enemy enemy;
	Long startime;
	Long currentime;

	public Enemy getEnemy() {
		return enemy;
	}

	public AbstractIA(Enemy enemy, Arena arena) {
		this.enemy = enemy;
		this.arena = arena;
		startime = System.currentTimeMillis();
	}

	public void update() {
		currentime = System.currentTimeMillis();
		int dir = 4;
		if (currentime - startime <= 5000) {
			startime = currentime;
			dir = new Random().nextInt(5);
			if (!rightDir(dir)) {
				dir = 4;
			}
		}

		enemy.setDirection(Direction.fromInt(dir));
	}

	private boolean canMove(float x, float y) {
		if (arena.getEdge().contains(x, y))
			return true;
		return false;
	}

	private boolean rightDir(int dir) {
		switch (dir) {
		case 0:
			return canMove(enemy.getX(), enemy.getY() - enemy.getMaxSpeed());
		case 1:
			return canMove(enemy.getX(), enemy.getY() + enemy.getMaxSpeed());
		case 2:
			return canMove(enemy.getX() + enemy.getMaxSpeed(), enemy.getY());
		case 3:
			return canMove(enemy.getX() - enemy.getMaxSpeed(), enemy.getY());
		default:
			break;
		}
		return false;
	}
}
