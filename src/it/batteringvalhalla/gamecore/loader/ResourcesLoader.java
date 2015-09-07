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
	public static ArrayList<Image> exitmenu_images;
	public static ArrayList<Image> optionmenu_images;
	public static ArrayList<Image> scoreboard_images;
	public static Font gothic;

	public ResourcesLoader() {

	}

	public static synchronized void loadPlayerImages() throws IOException {
		player = ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/actor/player.png"))
				.getScaledInstance(360, 320, java.awt.Image.SCALE_SMOOTH);
	}

	public static synchronized void loadMainMenuImages() throws IOException {
		mainmenu_images = new ArrayList<Image>();
		mainmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/play.png"))
				.getScaledInstance(200, 212, java.awt.Image.SCALE_SMOOTH));
		mainmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/h_play.png"))
						.getScaledInstance(200, 212,
								java.awt.Image.SCALE_SMOOTH));
		mainmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/option.png"))
						.getScaledInstance(165, 178,
								java.awt.Image.SCALE_SMOOTH));
		mainmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/h_option.png"))
						.getScaledInstance(165, 178,
								java.awt.Image.SCALE_SMOOTH));
		mainmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/exit.png"))
				.getScaledInstance(139, 149, java.awt.Image.SCALE_SMOOTH));
		mainmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/h_exit.png"))
						.getScaledInstance(139, 149,
								java.awt.Image.SCALE_SMOOTH));

	}

	public static synchronized void loadOptionMenuImages() throws IOException {
		optionmenu_images = new ArrayList<Image>();
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/pink_button_sound_on_morgaine1976.png")));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/pink_button_sound_off_morgaine1976.png")));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/bottonon.png")));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/Senzanome.png")));
		optionmenu_images.add(ImageIO.read(ResourcesLoader.class
				.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/off.png")));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/off2.png")));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/background/exit_background.png")));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/di4875BKT.png"))
						.getScaledInstance(100, 100,
								java.awt.Image.SCALE_SMOOTH));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/viki.png"))
						.getScaledInstance(100, 100,
								java.awt.Image.SCALE_SMOOTH));

	}

	public static synchronized void loadExitMenuImages() throws IOException {
		exitmenu_images = new ArrayList<Image>();
		exitmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/background/exit_background.png"))
						.getScaledInstance(678, 309,
								java.awt.Image.SCALE_SMOOTH));
		exitmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/yes.png"))
				.getScaledInstance(123, 133, java.awt.Image.SCALE_SMOOTH));
		exitmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/no.png"))
				.getScaledInstance(123, 133, java.awt.Image.SCALE_SMOOTH));

	}

	public static synchronized void loadScoreBoardImages() throws IOException {
		scoreboard_images = new ArrayList<Image>();
		scoreboard_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/background/scoreboard_background.png"))
						.getScaledInstance(678, 642,
								java.awt.Image.SCALE_SMOOTH));
		scoreboard_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/restart.png"))
						.getScaledInstance(123, 133,
								java.awt.Image.SCALE_SMOOTH));

	}

	public static synchronized void loadFont() throws IOException {
		try {
			gothic = Font
					.createFont(
							Font.TRUETYPE_FONT,
							ResourcesLoader.class
									.getClassLoader()
									.getResourceAsStream(
											"it/batteringvalhalla/assets/gui/fonts/Deutsch.ttf"));
		} catch (FontFormatException e) {
			System.out.println("Invalid Font Format, please pay attention");
			e.printStackTrace();
		}
	}
}
