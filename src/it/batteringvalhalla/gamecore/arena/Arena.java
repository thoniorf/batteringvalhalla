package it.batteringvalhalla.gamecore.arena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import it.batteringvalhalla.gamecore.collision.shape.CollisionShape;

public class Arena {

	private CollisionShape edge;
	private ArrayList<Point> spawn;

	public CollisionShape getEdge() {
		return edge;
	}

	public Arena() {
		edge = new CollisionShape(50, 150, 824, 658);
		spawn = new ArrayList<>();
		spawn.add(new Point(206, 142));
		spawn.add(new Point(206, 284));
		spawn.add(new Point(206, 426));
		spawn.add(new Point(200, 568));
		spawn.add(new Point(412, 142));
		spawn.add(new Point(200, 284));
		spawn.add(new Point(200, 284));
		spawn.add(new Point(200, 284));
	}

	public void paint(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect((int) edge.getX(), (int) edge.getY(), (int) edge.getWidth(), (int) edge.getHeight());

	}

}
