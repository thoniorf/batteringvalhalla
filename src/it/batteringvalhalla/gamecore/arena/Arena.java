package it.batteringvalhalla.gamecore.arena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import it.batteringvalhalla.gamecore.collision.shape.CollisionShape;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

public class Arena {

	private CollisionShape edge;
	private ArrayList<Point> spawn;
	private int x;
	private int y;
	private int width;
	private int height;

	public Arena() {
		this.x = 100;
		this.y = 50;
		this.width = 824;
		this.height = 658;
		edge = new CollisionShape(x, y, width, height);
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
		// g.fillRect((int) edge.getX(), (int) edge.getY(), (int)
		// edge.getWidth(), (int) edge.getHeight());
		g.drawImage(ResourcesLoader.mainmenu_images.get(9), x, y, null);

	}

}
