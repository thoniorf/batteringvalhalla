package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.IA.AbstractIA;
import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.AbstractGameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;
import it.batteringvalhalla.gamecore.object.actor.Direction;
import it.batteringvalhalla.gamecore.object.actor.Enemy;

import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld {
	// Objects
	Arena arena;
	CopyOnWriteArrayList<AbstractGameObject> objects;
	CopyOnWriteArrayList<AbstractIA> npc;
	Actor player;

	// behavior var
	Integer match;
	Integer enemies;
	Integer state;

	public GameWorld() {
		reset();
	}

	private void altPlayer() {
		player.setSpeedX(0f);
		player.setSpeedX(0f);
	}

	public void endGame() {
		setState(4);
	}

	public Integer getMatch() {
		return match;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<AbstractGameObject> getObjects() {
		return objects;
	}

	public Actor getPlayer() {
		return player;
	}

	// remember the enemies constant
	public void newMatch(Integer enemies) {
		altPlayer();
		this.match += 1;
		this.enemies = enemies;
		objects.clear();
		npc.clear();
		objects.add(player);
		for (int i = 0; i < enemies; i++) {
			Enemy tmp = new Enemy(400, 500, Direction.nord);
			objects.add(tmp);
			npc.add(new AbstractIA(tmp, arena));
		}
	}

	public void reset() {
		this.match = new Integer(0);
		this.enemies = new Integer(0);
		this.state = new Integer(0);
		arena = new Arena();
		objects = new CopyOnWriteArrayList<AbstractGameObject>();
		npc = new CopyOnWriteArrayList<AbstractIA>();
		player = new Actor(200, 300);
	}

	public void nextMatch() {
		altPlayer();
		newMatch(1);
	}

	public void paint(Graphics g) {
		arena.paint(g);
		for (AbstractGameObject obj : objects) {
			if (((Actor) obj).getLive() != 0) {
				obj.paint(g);
			}
		}

	}

	public void update() {
		Boolean isenemy = false;
		zOrder();
		for (int i = 0; i < npc.size(); i++) {
			if (npc.get(i).getEnemy().getLive() != 0) {
				// npc.get(i).update();
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Enemy) {
				isenemy = true;
			} else {
				isenemy = false;
			}
			if (((Actor) objects.get(i)).getLive() != 0) {
				objects.get(i).update();
				if (!arena.getEdge().contains(objects.get(i).getX(),
						objects.get(i).getY(), objects.get(i).getWidth(),
						objects.get(i).getHeight())) {
					((Actor) objects.get(i)).setLive(0);
					if (isenemy) {
						enemies -= 1;
					}

				}
			}
		}
		// !arena.getEdge().contains(player.getX(),
		// player.getY(),player.getWidth(), player.getHeight())
		if (player.getLive() == 0) {
			setState(4);
		}
		if (enemies == 0) {
			setState(3);
		}

	}

	public void zOrder() {
		objects.sort(null);
	}
}
