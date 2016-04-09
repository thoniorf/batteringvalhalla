package it.batteringvalhalla.gamecore.animation;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import it.batteringvalhalla.gamecore.object.direction.Direction;

public class Sprite implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer counter;
	private Integer currentFrame;
	private Integer endFrame;
	private transient BufferedImage frame;
	private Integer frameCol;
	private Integer frameHeight;
	private Integer frameRow;
	private Integer frameSpeed;
	private Integer framesPerRow;
	private Integer frameWidth;
	private Integer currentoffsetx;
	private Integer spriteoffsetxleft;
	private Integer spriteoffsetxright;
	private Integer spriteoffsety;
	private Integer imageHeight;
	private Integer imageWidth;
	private transient BufferedImage img;
	private Direction imgDir;

	public Sprite(Image img, Integer frameWidth, Integer frameHeight, Integer frameSpeed, Integer endFrame,
			Integer offset_left_x, Integer offset_right_x, Integer offset_y) {
		this.imgDir = Direction.est;
		this.imageWidth = new Integer(img.getWidth(null));
		this.imageHeight = new Integer(img.getHeight(null));
		this.frameWidth = new Integer(frameWidth);
		this.frameHeight = new Integer(frameHeight);
		this.spriteoffsetxleft = new Integer(offset_left_x);
		this.spriteoffsetxright = new Integer(offset_right_x);
		this.spriteoffsety = new Integer(offset_y);
		this.currentoffsetx = spriteoffsetxright;
		this.frameRow = new Integer(0);
		this.frameCol = new Integer(0);
		this.endFrame = new Integer(endFrame);
		this.frameSpeed = new Integer(frameSpeed);
		this.currentFrame = new Integer(1);
		framesPerRow = new Integer(imageWidth / frameWidth);
		this.counter = new Integer(0);
		this.img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
		this.frame = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = this.img.createGraphics();
		g2.setComposite(AlphaComposite.Src);
		g2.drawImage(img, 0, 0, null);
		g2.dispose();
		g2 = this.frame.createGraphics();
		g2.dispose();
	}

	public BufferedImage getFrame() {
		return frame;
	}

	public Integer getOffsetX() {
		return currentoffsetx;
	}

	public Integer getOffsetY() {
		return spriteoffsety;
	}

	public Integer getFrameCol() {
		return frameCol;
	}

	public Integer getFrameRow() {
		return frameRow;
	}

	public Integer getImageHeight() {
		return imageHeight;
	}

	public Integer getImageWidth() {
		return imageWidth;
	}

	public void update(Direction dir) {
		if (!(dir == Direction.stop && currentFrame == 1)) {
			currentFrame = (currentFrame + 1) % endFrame;
			counter = (counter + 1) % frameSpeed;
			frameCol = currentFrame % framesPerRow;
			frameRow = currentFrame / framesPerRow;
		}
		Graphics2D g2 = frame.createGraphics();
		g2.setComposite(AlphaComposite.Src);
		g2.drawImage(img.getSubimage(frameCol * frameWidth, frameRow * frameHeight, frameWidth, frameHeight), 0, 0,
				null);
		g2.dispose();
		currentoffsetx = spriteoffsetxright;
		if (dir == Direction.ovest || imgDir == Direction.ovest) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-frame.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			frame = op.filter(frame, null);
			currentoffsetx = spriteoffsetxleft;
			imgDir = ((dir == Direction.ovest && imgDir == Direction.est)
					|| (dir == Direction.est && imgDir == Direction.ovest)) ? dir : imgDir;
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		ImageIO.write(frame, "png", out);
		ImageIO.write(img, "png", out);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		frame = ImageIO.read(in);
		img = ImageIO.read(in);

	}
}
