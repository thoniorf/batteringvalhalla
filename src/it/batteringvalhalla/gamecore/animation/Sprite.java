package it.batteringvalhalla.gamecore.animation;

import it.batteringvalhalla.gamecore.object.actor.Direction;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Sprite {

	private Integer counter;
	private Integer currentFrame;
	private Integer endFrame;
	private BufferedImage frame;
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
	private Graphics2D g2;
	private Integer imageHeight;
	private Integer imageWidth;
	private BufferedImage img;
	private Direction imgDir;

	public Sprite(Image img, Integer imageWidth, Integer imageHeight,
			Integer frameWidth, Integer frameHeight, Integer frameSpeed,
			Integer endFrame, Integer offxleft, Integer offxright, Integer offy) {
		this.imgDir = Direction.est;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.spriteoffsetxleft = offxleft;
		this.spriteoffsetxright = offxright;
		this.spriteoffsety = offy;
		this.frameRow = new Integer(0);
		this.frameCol = new Integer(0);
		this.endFrame = endFrame;
		this.frameSpeed = frameSpeed;
		this.currentFrame = new Integer(1);
		framesPerRow = new Integer(imageWidth / frameWidth);
		this.counter = new Integer(0);
		this.img = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_ARGB);
		this.frame = new BufferedImage(frameWidth, frameHeight,
				BufferedImage.TYPE_INT_ARGB);
		g2 = this.img.createGraphics();
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
		g2 = (Graphics2D) frame.getGraphics();
		g2.setComposite(AlphaComposite.Src);
		g2.drawImage(img.getSubimage(frameCol * frameWidth, frameRow
				* frameHeight, frameWidth, frameHeight), 0, 0, null);
		g2.dispose();
		currentoffsetx = spriteoffsetxright;
		if (dir == Direction.ovest || imgDir == Direction.ovest) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-frame.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			frame = op.filter(frame, null);
			currentoffsetx = spriteoffsetxleft;
			imgDir = ((dir == Direction.ovest && imgDir == Direction.est) || (dir == Direction.est && imgDir == Direction.ovest)) ? dir
					: imgDir;
		}
	}
}
