package it.batteringvalhalla.gamecore.arena;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamegui.GameFrame;

public class Arena implements Serializable {
	private static final long serialVersionUID = 4678990743567406423L;
	private Shape shape;
	private transient BufferedImage background;
	private ArrayList<Point> spawn;
	private int x;
	private int y;
	private int width;
	private int height;

	public Arena(Image image) {
		background = (BufferedImage) image;
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

	public void setSpawn(List<Player> map_spawn) {
		spawn.clear();
		for (Player p : map_spawn) {
			spawn.add(p.getOrigin());
		}
	}

	public void paint(Graphics2D g) {
		g.drawImage(background, x, y, width, height, null);

	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		ImageIO.write(background, "png", out);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		background = ImageIO.read(in);

	}
}
