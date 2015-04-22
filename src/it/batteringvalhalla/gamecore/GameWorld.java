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
		for (GameObject obj : objects) {
			((Actor) obj).paint(g);
		}

	}

}
