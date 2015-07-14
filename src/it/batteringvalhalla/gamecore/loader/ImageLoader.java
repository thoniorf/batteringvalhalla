package it.batteringvalhalla.gamecore.loader;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static Image player;

	public ImageLoader() {

	}

	public static synchronized void loadImages() throws IOException {
		player = ImageIO.read(
				ImageLoader.class.getResource("../../assets/actor/player.png"))
				.getScaledInstance(90, 80, java.awt.Image.SCALE_SMOOTH);
	}

}
