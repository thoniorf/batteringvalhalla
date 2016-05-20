package it.batteringvalhalla;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.network.CharacterMessage;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;
import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamecore.vector2d.Vector2D;

public class SerializeBufferImage implements Serializable {
	private static final long serialVersionUID = 395576690695662956L;
	public transient BufferedImage image, image2;

	public SerializeBufferImage(Image source, int w, int h) {
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		image2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.drawImage(source, 0, 0, null);
		g2.dispose();
		g2 = image2.createGraphics();
		g2.setBackground(Color.BLACK);
		g2.dispose();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.flush();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ImageIO.write(image2, "png", buffer);
		out.writeInt(buffer.size());
		out.write(buffer.toByteArray());
		out.flush();
		buffer = new ByteArrayOutputStream();
		ImageIO.write(image, "png", buffer);
		out.writeInt(buffer.size());
		out.write(buffer.toByteArray());

	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		int length = in.readInt();
		byte[] buffer = new byte[length];
		in.readFully(buffer);
		image2 = ImageIO.read(new ByteArrayInputStream(buffer));
		length = in.readInt();
		buffer = new byte[length];
		in.readFully(buffer);
		image = ImageIO.read(new ByteArrayInputStream(buffer));
	}

	public static void main(String[] args) {
		try {
			ResourcesLoader.loadPlayerImages();
			Socket socket = new Socket(InetAddress.getLocalHost(), 8020);
			ObjectOutputStream s = new ObjectOutputStream(socket.getOutputStream());

			OnlineCharacter player = new OnlineCharacter("Player", new Point(50, 50), 1, 0, 1);
			System.out.println(player);
			s.writeUnshared(player);

			s.flush();
			player.setOrigin(new Point(25, 25));
			player.setVelocity(new Vector2D(new Point(1, 1)));
			player.setMoveDirection(Direction.nord);
			s.writeUnshared(player);

			s.flush();
			CharacterMessage message = new CharacterMessage(player);

			s.writeUnshared(message);
			System.out.println(message);
			s.flush();

			s.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
