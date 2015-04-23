package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.object.AbstractGameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameWorld {
	ArrayList<AbstractGameObject> objects;

	public GameWorld() {
		objects = new ArrayList<AbstractGameObject>();
		objects.add(new Actor(200, 300));
		objects.add(new Actor(400, 500));
	}

	public void update() {
		for (AbstractGameObject obj : objects) {
			obj.update();
		}

	}

	public ArrayList<AbstractGameObject> getObjects() {
		return objects;
	}

	public void paint(Graphics g) {
		g.drawString("World", 10, 10);
		for (AbstractGameObject obj : objects) {
			obj.paint(g);
		}

	}

}
