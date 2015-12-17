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

	private static GameWorld world;
	// Objects
	private Arena arena;
	private Actor player;
	private CopyOnWriteArrayList<AbstractGameObject> objects;

	// behavior vars
	private Integer match;
	private Integer enemies;
	private Integer n_enemy;
	private static Integer state;

	private GameWorld() {
		this.reset();
	}

	public static GameWorld instance() {
		if (world == null) {
			world = new GameWorld();
		}
		return world;
	}

	private void altPlayer() {
		this.player.setSpeedX(0f);
		this.player.setSpeedY(0f);
	}

	public void endGame() {
		setState(4);
	}

	public Integer getMatch() {
		return match;
	}

	public static Integer getState() {
		return state;
	}

	public static void setState(Integer s) {
		state = s;
	}

	public List<AbstractGameObject> getObjects() {
		return objects;
	}

	public Actor getPlayer() {
		return player;
	}

	// remember the enemies constant
	public void newMatch(int n_enemies) {
		this.altPlayer();
		this.match += 1;
		this.enemies = n_enemies;
		this.objects.clear();
		this.player.setX(arena.getSpawn().get(0).x);
		this.player.setY(arena.getSpawn().get(0).y);
		this.objects.add(player);
		for (int i = 0; i < n_enemies && i < arena.getSpawn().size(); i++) {
			Enemy tmp = new Enemy(arena.getSpawn().get(i + 1).x, arena.getSpawn().get(i + 1).y);
			tmp.setIA(new IAFocus(tmp, arena, objects), match);
			objects.add(tmp);
		}
	}

	public void reset() {
		this.match = new Integer(0);
		this.enemies = new Integer(0);
		this.n_enemy = new Integer(0);
		state = new Integer(0);
		this.arena = new Arena();
		this.objects = new CopyOnWriteArrayList<AbstractGameObject>();
		this.player = new Player(arena.getSpawn().get(0).x, arena.getSpawn().get(0).y);
	}

	public void nextMatch() {
		this.altPlayer();
		this.n_enemy = (n_enemy + 1) % 8;
		this.newMatch(n_enemy);

	}

	public void paint(Graphics g) {
		this.arena.paint(g);
		for (AbstractGameObject obj : objects) {
			if (((Actor) obj).getLive() != 0) {
				obj.paint(g);
			}
		}

	}

	public void update() {
		this.zOrder();
		for (int i = 0; i < objects.size(); i++) {
			if (((Actor) objects.get(i)).getLive() != 0) {
				objects.get(i).update();
				if (!arena.getEdge().contains(objects.get(i).getX(), objects.get(i).getY())) {
					((Actor) objects.get(i)).setLive(0);
					if (objects.get(i) instanceof Enemy) {
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
		this.objects.sort(null);
	}
}
