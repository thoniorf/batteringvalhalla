package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.object.GameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameWorld {
	ArrayList<GameObject> objects;

	public GameWorld() {
		objects = new ArrayList<GameObject>();
		objects.add(new Actor());
		objects.add(new Actor());
		objects.get(1).setX(50);
	}

	public void update() {
		for (GameObject obj : objects) {
			obj.update();
		}

	}

	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	public void paint(Graphics g) {
		g.drawString("World", 10, 10);
		for (GameObject obj : objects) {
			obj.paint(g);
		}

	}

}
