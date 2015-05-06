package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.AbstractGameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameWorld {
	ArrayList<AbstractGameObject> objects;
	Arena arena;
	Integer enemies;
	Integer match;

	public GameWorld() {
		enemies = new Integer(0);
		match = new Integer(0);
		arena = new Arena();
		objects = new ArrayList<AbstractGameObject>();
		objects.add(new Actor(200, 300));
		init(1, 1);

	}

	public void update() {
		if (!arena.getEdge().contains(objects.get(0).getX(),
				objects.get(0).getY())) {
			System.out.println("Game Over");
			System.exit(0);
		}
			if (enemies == 0) {
				nextMatch();
			}
		for (AbstractGameObject obj : objects) {
			if (((Actor) obj).getLive() != 0) {
				obj.update();
				if (!arena.getEdge().contains(obj.getX(), obj.getY())) {
					((Actor) obj).setLive(0);
					enemies -= 1;
				}
			}
		}
	}

	public ArrayList<AbstractGameObject> getObjects() {
		return objects;
	}

	public void paint(Graphics g) {
		arena.paint(g);
		for (AbstractGameObject obj : objects) {
			if (((Actor) obj).getLive() != 0) {
				obj.paint(g);
			}
		}
		g.drawString("Match:" + match.toString(), 10, 10);
	}

	public void nextMatch() {
		this.match += 1;
		init(1, match);
	}

	public void init(Integer enemies, Integer match) {
		this.match = match;
		this.enemies = enemies;
		for (int i = 0; i < enemies; i++) {
			objects.add(new Actor(400, 500));
		}
	}
}
