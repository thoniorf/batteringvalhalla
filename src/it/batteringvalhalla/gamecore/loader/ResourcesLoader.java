package it.batteringvalhalla.gamecore.loader;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ResourcesLoader {

	public static Image player;
	public static ArrayList<Image> mainmenu_images;
	public static Font gothic;

	public ResourcesLoader() {

	}

	public static synchronized void loadPlayerImages() throws IOException {
		player = ImageIO.read(
				ResourcesLoader.class
						.getResource("../../assets/actor/player.png"))
				.getScaledInstance(90, 80, java.awt.Image.SCALE_SMOOTH);
	}

	public static synchronized void loadMainMenuImages() throws IOException {
		mainmenu_images = new ArrayList<Image>();
		mainmenu_images.add(ImageIO.read(
				ResourcesLoader.class
						.getResource("../../assets/gui/menu/icon/play.png"))
				.getScaledInstance(200, 212, java.awt.Image.SCALE_SMOOTH));
		mainmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getResource("../../assets/gui/menu/icon/hover/h_play.png"))
						.getScaledInstance(200, 212,
								java.awt.Image.SCALE_SMOOTH));
		mainmenu_images.add(ImageIO.read(
				ResourcesLoader.class
						.getResource("../../assets/gui/menu/icon/option.png"))
				.getScaledInstance(165, 178, java.awt.Image.SCALE_SMOOTH));
		mainmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getResource("../../assets/gui/menu/icon/hover/h_option.png"))
						.getScaledInstance(165, 178,
								java.awt.Image.SCALE_SMOOTH));
		mainmenu_images.add(ImageIO.read(
				ResourcesLoader.class
						.getResource("../../assets/gui/menu/icon/exit.png"))
				.getScaledInstance(139, 149, java.awt.Image.SCALE_SMOOTH));
		mainmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getResource("../../assets/gui/menu/icon/hover/h_exit.png"))
						.getScaledInstance(139, 149,
								java.awt.Image.SCALE_SMOOTH));

	}

	public static synchronized void loadFont() throws IOException {
		try {
			gothic = Font.createFont(Font.TRUETYPE_FONT, ResourcesLoader.class
					.getResourceAsStream("../../assets/gui/fonts/Deutsch.ttf"));
		} catch (FontFormatException e) {
			System.out.println("Invalid Font Format, please pay attention");
			e.printStackTrace();
		}
	}
}
