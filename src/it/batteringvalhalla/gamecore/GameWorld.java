package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.IA.IAICanMove;
import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.AbstractGameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld {
	// Objects
	Arena arena;
	List<AbstractGameObject> objects;
	List<IAICanMove> npc;
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
		player.setScore(match);
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
		this.match += 1;
		this.enemies = enemies;
		objects.clear();
		npc.clear();
		objects.add(player);
		for (int i = 0; i < enemies; i++) {
			Actor tmp = new Actor(400, 500);
			objects.add(tmp);
			npc.add(new IAICanMove(tmp, arena));
		}
	}

	public void reset() {
		this.match = new Integer(0);
		this.enemies = new Integer(0);
		this.state = new Integer(0);
		arena = new Arena();
		objects = new CopyOnWriteArrayList<AbstractGameObject>();
		npc = new CopyOnWriteArrayList<IAICanMove>();
		player = new Actor(200, 300);
		altPlayer();
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
		AbstractGameObject obj;
		Iterator<AbstractGameObject> iter = objects.iterator();
		obj = iter.next();
		obj.update();
		if (!arena.getEdge().contains(player.getX(), player.getY())) {
			setState(4);
		}

		int i = 0;
		while (iter.hasNext()) {
			obj = iter.next();
			if (((Actor) obj).getLive() != 0) {
				npc.get(i).moveActor();
				obj.update();
				if (!arena.getEdge().contains(obj.getX(), obj.getY())) {
					((Actor) obj).setLive(0);
					enemies -= 1;
					if (enemies == 0) {
						setState(3);
					}
				}
			}
			i++;
		}
	}

	public void zOrder() {
		objects.sort(null);
	}
}
