package it.batteringvalhalla.gamecore.loader;

import it.batteringvalhalla.gamecore.animation.Sprite;
import it.batteringvalhalla.gamecore.object.actor.Direction;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ResourcesLoader {

	public static Image player;
	public static Image actor_body;
	public static ArrayList<Image> actor_head;
	private static int N_head = 0;
	private static int N_mount = 4;
	private static int N_weapon = 1;
	public static ArrayList<Image> actor_mount;
	public static ArrayList<Image> actor_weapon;
	public static ArrayList<Image> mainmenu_images;
	public static ArrayList<Image> exitmenu_images;
	public static ArrayList<Image> optionmenu_images;
	public static ArrayList<Image> scoreboard_images;
	public static Font gothic;

	// editor
	public static List<Image> imageHead;
	public static List<Image> imageBust;
	public static List<Image> imageGoat;
	public static List<Image> rightArrow;
	public static List<Image> leftArrow;
	public static Image Sfondo;
	public static int sizeHead = 4;
	public static int sizeBust = 4;
	public static int sizeGoat = 3;

	public ResourcesLoader() {

	}

	public static synchronized void loadPlayerImages() throws IOException {
		actor_head = new ArrayList<Image>();
		actor_mount = new ArrayList<Image>();
		actor_weapon = new ArrayList<Image>();
		actor_body = ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/actor/bodies/player.png"));

		for (int i = 1; i <= N_head; i++) {
			actor_head.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
					.getResource("it/batteringvalhalla/assets/actor/heads/#########")));
		}

		for (int i = 1; i <= N_mount; i++) {
			actor_mount.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
					.getResource("it/batteringvalhalla/assets/actor/rams/mount" + i + ".png")));
		}

		for (int i = 1; i <= N_weapon; i++) {
			actor_weapon.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
					.getResource("it/batteringvalhalla/assets/actor/weapons/weapon" + i + ".png")));
		}

	}

	public static synchronized void loadMainMenuImages() throws IOException {
		mainmenu_images = new ArrayList<Image>();

		mainmenu_images.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/gui/menu/icon/play.png")));

		mainmenu_images.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_play.png")));

		mainmenu_images.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/s_h_play.png")));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/option.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_option.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/editor.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_editor.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/exit.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_exit.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/arena/background.png")));

		mainmenu_images.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/gui/background-ui.png")));
	}

	public static synchronized void loadOptionMenuImages() throws IOException {
		optionmenu_images = new ArrayList<Image>();

		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/on.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_on.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/off.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_off.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/background/option_background.png"))
				.getScaledInstance(768, 640, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/back.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_back.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		optionmenu_images.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/gui/menu/all_keys.png")));

	}

	public static synchronized void loadExitMenuImages() throws IOException {
		exitmenu_images = new ArrayList<Image>();

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/background/exit_background.png"))
				.getScaledInstance(512, 256, java.awt.Image.SCALE_SMOOTH));
		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/yes_red.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/no_blue.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_yes_red.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_no_blue.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/yes_blue.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_yes_blue.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/no_red.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_no_red.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

	}

	public static synchronized void loadScoreBoardImages() throws IOException {
		scoreboard_images = new ArrayList<Image>();

		scoreboard_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/restart.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

		scoreboard_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_restart.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

	}

	public static synchronized void loadFont() throws IOException {
		try {
			gothic = Font.createFont(Font.TRUETYPE_FONT, ResourcesLoader.class.getClassLoader()
					.getResourceAsStream("it/batteringvalhalla/assets/gui/fonts/Deutsch.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, ResourcesLoader.class.getClassLoader()
					.getResourceAsStream("it/batteringvalhalla/assets/gui/fonts/Deutsch.ttf")));
		} catch (FontFormatException e) {
			System.out.println("Invalid Font Format, please pay attention");
			e.printStackTrace();
		}
	}

	public static synchronized void loadEditorImages() throws IOException {
		imageBust = new ArrayList<Image>();
		imageGoat = new ArrayList<Image>();
		imageHead = new ArrayList<Image>();
		leftArrow = new ArrayList<Image>();
		rightArrow = new ArrayList<Image>();
		Sprite tmp;

//		for (int i = 0; i <ResourcesLoader.actor_head.size(); i++) {
//			tmp=new Sprite(ResourcesLoader.actor_head.get(i), ResourcesLoader.actor_head.get(i).getWidth(null),
//					ResourcesLoader.actor_head.get(i).getHeight(null), 115, 94, 1, 1, 0, 0, 0);
//			tmp.update(Direction.est);
//			imageHead.add(tmp.getFrame());
//		}
		for (int i = 0; i < ResourcesLoader.actor_weapon.size(); i++) {
			tmp= new Sprite(ResourcesLoader.actor_weapon.get(i),
					ResourcesLoader.actor_weapon.get(i).getWidth(null), ResourcesLoader.actor_weapon.get(i).getHeight(null),
					103, 76, 1, 3, 0, 0, 0);
			tmp.update(Direction.est);
			tmp.update(Direction.est);
			
			imageBust.add(tmp.getFrame());
		}
		for (int i = 0; i < ResourcesLoader.actor_mount.size(); i++) {
			tmp=new Sprite(ResourcesLoader.actor_mount.get(i), ResourcesLoader.actor_mount.get(i).getWidth(null),
					ResourcesLoader.actor_mount.get(i).getHeight(null), 117, 88, 1, 1, 0, 0, 0);
			tmp.update(Direction.est);
			imageGoat.add(tmp.getFrame());
			

		}
		Sfondo = ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/editor/sfondo.png"))
				.getScaledInstance(123, 133, java.awt.Image.SCALE_SMOOTH);
		leftArrow.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/back.png"))
				.getScaledInstance(123, 133, java.awt.Image.SCALE_SMOOTH));

		leftArrow.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_back.png"))
				.getScaledInstance(123, 133, java.awt.Image.SCALE_SMOOTH));

		rightArrow.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/forward.png"))
				.getScaledInstance(123, 133, java.awt.Image.SCALE_SMOOTH));

		rightArrow.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_forward.png"))
				.getScaledInstance(123, 133, java.awt.Image.SCALE_SMOOTH));
	}
}
