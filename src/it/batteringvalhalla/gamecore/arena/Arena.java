package it.batteringvalhalla.gamecore.arena;

import java.awt.Graphics;

import javafx.scene.shape.Circle;

public class Arena {

	private Circle edge;

	public Circle getEdge() {
		return edge;
	}

	public Arena() {
		edge = new Circle(68, 200, 500);
	}

	public void paint(Graphics g) {
		g.drawOval(68, 200, 500, 500);

	}

}
