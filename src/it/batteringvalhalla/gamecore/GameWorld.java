package it.batteringvalhalla.gamecore;

import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.batteringvalhalla.gamecore.IA.IAFocus;
import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.AbstractGameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;
import it.batteringvalhalla.gamecore.object.actor.Enemy;
import it.batteringvalhalla.gamecore.object.actor.Player;

public class GameWorld {
	// Objects
	Arena arena;
	CopyOnWriteArrayList<AbstractGameObject> objects;
	Actor player;

	// behavior var
	Integer match;
	Integer enemies;
	Integer n_enemy;
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
	public void newMatch(int n_enemies) {
		altPlayer();
		this.match += 1;
		this.enemies = n_enemies;
		objects.clear();
		player.setX(arena.getSpawn().get(0).x);
		player.setY(arena.getSpawn().get(0).y);
		objects.add(player);
		for (int i = 0; i < n_enemies && i < arena.getSpawn().size(); i++) {
			Enemy tmp = new Enemy(arena.getSpawn().get(i + 1).x, arena.getSpawn().get(i + 1).y);
			tmp.setIA(new IAFocus(tmp, arena, objects));
			objects.add(tmp);
		}
	}

	public void reset() {
		this.match = new Integer(0);
		this.enemies = new Integer(0);
		this.n_enemy = new Integer(0);
		this.state = new Integer(0);
		arena = new Arena();
		objects = new CopyOnWriteArrayList<AbstractGameObject>();
		player = new Player(arena.getSpawn().get(0).x, arena.getSpawn().get(0).y);
	}

	public void nextMatch() {
		altPlayer();
		n_enemy = (n_enemy + 1) % 8;
		newMatch(n_enemy);
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
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Enemy) {
				isenemy = true;
			} else {
				isenemy = false;
			}
			if (((Actor) objects.get(i)).getLive() != 0) {
				objects.get(i).update();
				if (!arena.getEdge().contains(objects.get(i).getX(), objects.get(i).getY())) {
					((Actor) objects.get(i)).setLive(0);
					if (isenemy) {
						enemies -= 1;
					}

				}
			}
		}
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
