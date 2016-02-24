package it.batteringvalhalla.gamecore.ia;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Random;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.Entity;
import it.batteringvalhalla.gamecore.object.actor.AbstractActor;
import it.batteringvalhalla.gamecore.object.actor.Actor;
import it.batteringvalhalla.gamecore.object.actor.Enemy;
import it.batteringvalhalla.gamecore.object.direction.Direction;

public class IAFocus extends AbstractIA {
	private static final int timeMove = 2000;
	private int timePause;
	private AbstractActor myEnemy;
	private List<Entity> players;

	public IAFocus(Enemy npc, Arena arena, List<Entity> players) {
		super(npc, arena);
		this.players = players;
		timePause = 1000;

	}

	protected AbstractActor getMyEnemy() {
		AbstractActor tmp = null;
		int radiusTmp, radiusPlayer;
		for (int i = 0; i < players.size(); i++) {

			if (players.get(i) instanceof Actor) {
				if (npc != players.get(i) && players.get(i).getAlive()) {
					if (tmp != null) {
						radiusTmp = Math.abs(tmp.getOrigin().x - npc.getOrigin().x)
								+ Math.abs(tmp.getOrigin().y - npc.getOrigin().y);
						radiusPlayer = Math.abs(players.get(i).getOrigin().x - npc.getOrigin().x)
								+ Math.abs(players.get(i).getOrigin().y - npc.getOrigin().y);
						if (radiusTmp > radiusPlayer)
							tmp = (AbstractActor) players.get(i);
					} else
						tmp = (AbstractActor) players.get(i);
				}
			}
		}
		if (tmp == null)
			tmp = npc;
		return tmp;
	}

	@Override
	public void levelUp(int level) {
		if (timePause > 199)
			timePause -= level * 200;
	}

	@Override
	public void update() {
		myEnemy = getMyEnemy();

		currentime = System.currentTimeMillis();

		if (npc.getShape().intersects((Rectangle2D) myEnemy.getShape())) {
			startime = Math.abs(timeMove - currentime);

		}

		if (currentime - startime < timeMove && (this.canMove(npc.getOrigin().x, npc.getOrigin().y))) {

			if (Math.abs(myEnemy.getOrigin().x - npc.getOrigin().x) >= Math
					.abs(myEnemy.getOrigin().y - npc.getOrigin().y)) {
				if (myEnemy.getOrigin().x > npc.getOrigin().x)
					npc.setMoveDirection(Direction.est);
				else
					npc.setMoveDirection(Direction.ovest);
			} else {
				if (myEnemy.getOrigin().y > npc.getOrigin().y)
					npc.setMoveDirection(Direction.sud);
				else
					npc.setMoveDirection(Direction.nord);
			}
		} else if (currentime - startime >= timeMove + timePause) {
			startime = currentime;
		} else if (currentime - startime == timeMove)
			randomMove();
		else {
			while (!rightDir(Direction.toInt(npc.getMoveDirection()))) {
				randomMove();
			}
		}

	}

	private void randomMove() {
		int dir = new Random().nextInt(5) + 1;
		npc.setMoveDirection(Direction.fromInt(dir));

	}

}
