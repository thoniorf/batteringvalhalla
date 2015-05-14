package it.batteringvalhalla.gamecore.arena;

import it.batteringvalhalla.gamecore.collision.shape.CollisionShape;

import java.awt.Color;
import java.awt.Graphics;

public class Arena {

	private CollisionShape edge;

	public CollisionShape getEdge() {
		return edge;
	}

	public Arena() {
		edge = new CollisionShape(50, 150, 918, 518);
	}

	public void paint(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect((int) edge.getX(), (int) edge.getY(), (int) edge.getWidth(),
				(int) edge.getHeight());

	}

}
