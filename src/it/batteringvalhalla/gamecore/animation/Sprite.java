package it.batteringvalhalla.gamecore.animation;

import it.batteringvalhalla.gamecore.object.actor.Direction;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Sprite {

	private BufferedImage img;
	private BufferedImage frame;
	private Integer framesPerRow;
	private Integer frameWidth;
	private Integer frameHeight;
	private Integer endFrame;
	private Integer frameSpeed;
	private Integer currentFrame;
	private Integer counter;
	private Integer frameRow;
	private Integer frameCol;
	private Direction imgDir;
	private Graphics2D g2;

	public Integer getFrameRow() {
		return frameRow;
	}

	public Integer getFrameCol() {
		return frameCol;
	}

	public Sprite(Image img, Integer imageWidth, Integer imageHeight,
			Integer frameWidth, Integer frameHeight, Integer frameSpeed,
			Integer endFrame) {
		this.imgDir = Direction.est;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.frameRow = new Integer(1);
		this.frameCol = new Integer(1);
		this.endFrame = endFrame;
		this.frameSpeed = frameSpeed;
		this.currentFrame = new Integer(1);
		this.counter = new Integer(0);
		this.img = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		this.frame = new BufferedImage(frameWidth, frameHeight,
				BufferedImage.TYPE_INT_RGB);
		g2 = this.img.createGraphics();
		g2.drawImage(img, 0, 0, null);
		g2.dispose();
		g2 = this.frame.createGraphics();
		g2.dispose();
		framesPerRow = new Integer(imageWidth / frameWidth);
	}

	public void update(float speedx, float speedy) {

		currentFrame = (currentFrame + 1) % endFrame;
		counter = (counter + 1) % frameSpeed;
		frameCol = currentFrame % framesPerRow;
		frameRow = currentFrame / framesPerRow;

		g2 = (Graphics2D) frame.getGraphics();
		g2.drawImage(img.getSubimage(frameCol * frameWidth, frameRow
				* frameHeight, frameWidth, frameHeight), 0, 0, null);
		g2.dispose();
		// if (dir != imgDir && dir != Direction.stop) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-frame.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		frame = op.filter(frame, null);
		// imgDir = dir;
		// }
	}

	public BufferedImage getFrame() {
		return frame;
	}
}
