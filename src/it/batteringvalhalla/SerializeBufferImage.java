package it.batteringvalhalla;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

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
			Image body = ImageIO.read(ResourcesLoader.class.getClassLoader()
					.getResource("it/batteringvalhalla/assets/actor/bodies/body_1.png"));
			SerializeBufferImage obj = new SerializeBufferImage(body, 200, 200);
			SerializeBufferImage obj1 = new SerializeBufferImage(body, 50, 50);
			Socket socket = new Socket(InetAddress.getLocalHost(), 8020);
			OutputStream o = socket.getOutputStream();
			ObjectOutput s = new ObjectOutputStream(o);
			s.writeObject(obj);
			s.flush();
			s.writeObject(obj1);
			s.flush();
			s.close();
			o.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
