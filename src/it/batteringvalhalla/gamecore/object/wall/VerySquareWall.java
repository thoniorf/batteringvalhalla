package it.batteringvalhalla.gamecore.object.wall;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.AbstractEntity;

public class VerySquareWall extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1896230426201017391L;
	private Integer maxLife;
	private Integer life;
	private transient BufferedImage image;

	public VerySquareWall(Integer x, Integer y, Integer maxLife) {
		super(new Point(x, y));
		this.maxLife = maxLife;
		setLife(maxLife);
		if (maxLife > 0) {
			image = (BufferedImage) ResourcesLoader.walls_images.get(0);
		} else {
			image = (BufferedImage) ResourcesLoader.walls_images.get(1);
		}
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

	public Integer getLife() {
		return life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(image, origin.x - width / 2, origin.y - height / 2, width, height, null);
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		ImageIO.write(image, "jpg", out);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		image = ImageIO.read(in);

	}
}
