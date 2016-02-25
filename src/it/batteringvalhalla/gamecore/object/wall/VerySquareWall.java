package it.batteringvalhalla.gamecore.object.wall;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.AbstractEntity;

public class VerySquareWall extends AbstractEntity {
	private Integer maxLife;
	private Integer life;
	private Image image;

	public VerySquareWall(Integer x, Integer y, Integer maxLife) {
		super(new Point(x, y));
		this.maxLife = maxLife;
		life = maxLife;
		image = ResourcesLoader.walls_images.get(0);
	}

	public Rectangle getRectangle() {
		return (Rectangle) shape;
	}

	public void postCollision() {
	}

	public Integer getMaxLife() {
		return maxLife;
	}

	public Image getImage() {
		return image;
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(image, origin.x - width / 2, origin.y - height / 2, width, height, null);

		// debug
		g.draw(shape);

	}
}
