package it.batteringvalhalla.gamecore.loader;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ResourcesLoader {

	public static Image player;
	public static ArrayList<Image> mainmenu_images;
	public static ArrayList<Image> exitmenu_images;
	public static ArrayList<Image> optionmenu_images;
	public static ArrayList<Image> scoreboard_images;
	public static Font gothic;
	
	//editor
	public static List<Image> imageHead;
	public static List<Image> imageBust;
	public static List<Image> imageGoat;
	public static List<Image> rightArrow;
	public static List<Image> leftArrow;
	public static Image Sfondo;
	public static int sizeHead=4;
	public static int sizeBust=4;
	public static int sizeGoat=3;
	

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

		mainmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/w.png"))
						.getScaledInstance(150, 160,
								java.awt.Image.SCALE_SMOOTH));

		mainmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/h_editor.png"))
						.getScaledInstance(150, 160,
								java.awt.Image.SCALE_SMOOTH));

	}

	public static synchronized void loadOptionMenuImages() throws IOException {
		optionmenu_images = new ArrayList<Image>();

		optionmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/on.png"))
				.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/on_hover.png"))
						.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/off.png"))
				.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/off_hover.png"))
						.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/background/exit_background.png"))
						.getScaledInstance(600, 600,
								java.awt.Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/back.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/h_back.png"))
						.getScaledInstance(100, 100,
								java.awt.Image.SCALE_SMOOTH));

		optionmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/w.png"))
				.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

		optionmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/s.png"))
				.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

		optionmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/a.png"))
				.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

		optionmenu_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource(
						"it/batteringvalhalla/assets/gui/menu/icon/d.png"))
				.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

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

		exitmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/h_yes.png"))
						.getScaledInstance(123, 133,
								java.awt.Image.SCALE_SMOOTH));

		exitmenu_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/h_no.png"))
						.getScaledInstance(123, 133,
								java.awt.Image.SCALE_SMOOTH));

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

		scoreboard_images
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/gui/menu/icon/hover/h_restart.png"))
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
	
	public static synchronized void loadEditorImages()throws IOException{
		imageBust=new ArrayList<Image>();
		imageGoat=new ArrayList<Image>();
		imageHead=new ArrayList<Image>();
		leftArrow=new ArrayList<Image>();
		rightArrow=new ArrayList<Image>();
		
		
		for(int i=0;i<sizeHead;i++){
			imageHead
			.add(ImageIO
					.read(ResourcesLoader.class
							.getClassLoader()
							.getResource(
									"it/batteringvalhalla/assets/editor/head"+i+".png"))
					.getScaledInstance(123, 133,
							java.awt.Image.SCALE_SMOOTH));
		}
		for(int i=0;i<sizeBust;i++){
			imageBust
			.add(ImageIO
					.read(ResourcesLoader.class
							.getClassLoader()
							.getResource(
									"it/batteringvalhalla/assets/editor/bust"+i+".png"))
					.getScaledInstance(123, 133,
							java.awt.Image.SCALE_SMOOTH));}
			for(int i=0;i<sizeGoat;i++){
				imageGoat
				.add(ImageIO
						.read(ResourcesLoader.class
								.getClassLoader()
								.getResource(
										"it/batteringvalhalla/assets/editor/Goat"+i+".png"))
						.getScaledInstance(123, 133,
								java.awt.Image.SCALE_SMOOTH));
	
				}
	Sfondo=ImageIO
			.read(ResourcesLoader.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/editor/Sfondo.png"))
			.getScaledInstance(123, 133,
					java.awt.Image.SCALE_SMOOTH);
	leftArrow
	.add(ImageIO
			.read(ResourcesLoader.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/gui/menu/icon/left.png"))
			.getScaledInstance(123, 133,
					java.awt.Image.SCALE_SMOOTH));
	
	leftArrow
	.add(ImageIO
			.read(ResourcesLoader.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/gui/menu/icon/hover/h_left.png"))
			.getScaledInstance(123, 133,
					java.awt.Image.SCALE_SMOOTH));
	
	rightArrow
	.add(ImageIO
			.read(ResourcesLoader.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/gui/menu/icon/right.png"))
			.getScaledInstance(123, 133,
					java.awt.Image.SCALE_SMOOTH));
	
	rightArrow
	.add(ImageIO
			.read(ResourcesLoader.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/gui/menu/icon/hover/h_right.png"))
			.getScaledInstance(123, 133,
					java.awt.Image.SCALE_SMOOTH));
	}
}
