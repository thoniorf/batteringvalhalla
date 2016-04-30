package it.batteringvalhalla;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DeserializeServer {

	public DeserializeServer() {

	}

	public static void main(String[] args) {

		ServerSocket server = null;
		Socket socket = null;

		try {
			server = new ServerSocket(8020);
			socket = server.accept();
			InputStream o = socket.getInputStream();
			ObjectInput i = new ObjectInputStream(o);
			final SerializeBufferImage sprite = (SerializeBufferImage) i.readObject();
			final SerializeBufferImage sprite2 = (SerializeBufferImage) i.readObject();
			i.close();
			JFrame frame = new JFrame();
			JPanel panel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					g.drawImage(sprite.image2, 0, 0, null);
					g.drawImage(sprite2.image2, 0, 0, null);
					g.drawImage(sprite.image, 0, 0, null);
					g.drawImage(sprite2.image, 50, 50, null);
				};
			};
			panel.setBackground(Color.CYAN);
			panel.setVisible(true);
			frame.setContentPane(panel);
			frame.pack();
			frame.setVisible(true);
			panel.repaint();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
