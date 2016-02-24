package it.batteringvalhalla.gamecore.arena;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import it.batteringvalhalla.gamegui.GameFrame;

public class Arena {

	private Shape shape;
	private Image background;
	private ArrayList<Point> spawn;
	private int x;
	private int y;
	private int width;
	private int height;

	public Arena(Image image) {
		background = image;
		this.x = GameFrame.size.width / 2 - background.getWidth(null) / 2;
		this.y = GameFrame.size.height / 2 - background.getHeight(null) / 2;
		this.width = background.getWidth(null);
		this.height = background.getHeight(null);
		shape = new Rectangle(x, y, width, height);
		spawn = new ArrayList<>();
		createSpawn();
	}

	private void createSpawn() {
		int cols = width / 4;
		int rows = height / 4;
		for (int i = 0; i < 4; i++) {
			if (i == 0 || i == 4 - 1) {
				for (int j = 0; j < 4; j++) {
					spawn.add(new Point(x + 50 + cols * j, y + 50 + rows * i));
				}
			} else {
				spawn.add(new Point(x + 50, y + 50 + rows * i));
				spawn.add(new Point(x + 50 + cols * 3, y + 50 + rows * i));
			}

		}
	}

	public Shape getShape() {
		return shape;
	}

	public ArrayList<Point> getSpawn() {
		return spawn;
	}

	public void paint(Graphics2D g) {
		g.drawImage(background, x, y, width, height, null);

	}

}
