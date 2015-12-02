package it.batteringvalhalla.gamecore.arena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import it.batteringvalhalla.gamecore.collision.shape.CollisionShape;

public class Arena {

	private CollisionShape edge;
	private ArrayList<Point> spawn;

	public Arena() {
		edge = new CollisionShape(100, 50, 824, 658);
		spawn = new ArrayList<>();
		// one
		spawn.add(new Point(200, 160));
		// two
		spawn.add(new Point(858, 620));
		// three
		spawn.add(new Point(858, 160));
		// four
		spawn.add(new Point(200, 620));
		// five
		spawn.add(new Point(488, 160));
		// six
		spawn.add(new Point(858, 400));
		// seven
		spawn.add(new Point(200, 400));
		// eight players
		spawn.add(new Point(858, 620));
	}

	public CollisionShape getEdge() {
		return edge;
	}

	public ArrayList<Point> getSpawn() {
		return spawn;
	}

	public void paint(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect((int) edge.getX(), (int) edge.getY(), (int) edge.getWidth(), (int) edge.getHeight());

	}

}
