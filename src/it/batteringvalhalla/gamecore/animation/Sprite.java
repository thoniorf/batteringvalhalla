package it.batteringvalhalla.gamecore.animation;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import it.batteringvalhalla.gamecore.object.direction.Direction;

public class Sprite implements Serializable {

	private static final long serialVersionUID = -8055950592352099383L;
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

	public Sprite(Image source, Integer frameWidth, Integer frameHeight, Integer frameSpeed, Integer endFrame,
			Integer offset_y) {
		this.imgDir = Direction.est;
		this.imageWidth = new Integer(source.getWidth(null));
		this.imageHeight = new Integer(source.getHeight(null));
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.spriteoffsety = offset_y;
		this.frameRow = 0;
		this.frameCol = 0;
		this.endFrame = endFrame;
		this.frameSpeed = frameSpeed;
		this.currentFrame = 1;
		this.framesPerRow = new Integer(imageWidth / frameWidth);
		this.counter = 0;
		this.img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
		this.frame = new BufferedImage(this.frameWidth, this.frameHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = this.img.createGraphics();
		g2.setComposite(AlphaComposite.Src);
		g2.drawImage(source, 0, 0, null);
		g2.dispose();
		g2 = this.frame.createGraphics();
		g2.dispose();
	}

	public void update(Direction dir) {
		if (!((dir == Direction.stop) && (currentFrame == 1))) {
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
		if ((dir == Direction.ovest) || (imgDir == Direction.ovest)) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-frame.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			frame = op.filter(frame, null);
			currentoffsetx = spriteoffsetxleft;
			imgDir = (((dir == Direction.ovest) && (imgDir == Direction.est))
					|| ((dir == Direction.est) && (imgDir == Direction.ovest))) ? dir : imgDir;
		}
	}

	public BufferedImage getFrame() {
		return frame;
	}

	public BufferedImage getImg() {
		return img;
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

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ImageIO.write(frame, "png", buffer);
		out.writeInt(buffer.size());
		out.write(buffer.toByteArray());
		buffer = new ByteArrayOutputStream();
		ImageIO.write(img, "png", buffer);
		out.writeInt(buffer.size());
		out.write(buffer.toByteArray());
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		int length = in.readInt();
		byte[] buffer = new byte[length];
		in.readFully(buffer);
		frame = ImageIO.read(new ByteArrayInputStream(buffer));
		length = in.readInt();
		buffer = new byte[length];
		in.readFully(buffer);
		img = ImageIO.read(new ByteArrayInputStream(buffer));

	}

}
