package it.batteringvalhalla.gamecore.loader;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import it.batteringvalhalla.gamecore.animation.Sprite;
import it.batteringvalhalla.gamecore.object.direction.Direction;

public class ResourcesLoader {

	public static Image player;
	private static int N_head = 3;
	private static int N_mount = 4;
	private static int N_body = 3;
	public static ArrayList<Image> actor_head;
	public static ArrayList<Image> actor_mount;
	public static ArrayList<Image> actor_body;
	public static ArrayList<Image> mainmenu_images;
	public static ArrayList<Image> exitmenu_images;
	public static ArrayList<Image> optionmenu_images;
	public static ArrayList<Image> scoreboard_images;
	public static ArrayList<Image> walls_images;
	public static Font gothic;
	// header
	public static Image header;
	// icons
	public static HashMap<String, Image> images = new HashMap<>();

	// editor
	public static List<Image> imageHead;
	public static List<Image> imageBust;
	public static List<Image> imageGoat;
	public static List<Image> rightArrow;
	public static List<Image> leftArrow;

	public static synchronized void loadPlayerImages() throws IOException {
		actor_head = new ArrayList<Image>();
		actor_mount = new ArrayList<Image>();
		actor_body = new ArrayList<Image>();

		for (int i = 1; i <= N_head; i++) {
			actor_head.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
					.getResource("it/batteringvalhalla/assets/actor/heads/head_" + i + ".png")));
		}

		for (int i = 1; i <= N_mount; i++) {
			actor_mount.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
					.getResource("it/batteringvalhalla/assets/actor/rams/mount" + i + ".png")));
		}

		for (int i = 1; i <= N_body; i++) {
			// body
			actor_body.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
					.getResource("it/batteringvalhalla/assets/actor/bodies/body_" + i + ".png")));
			// arm
			actor_body.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
					.getResource("it/batteringvalhalla/assets/actor/bodies/arm_" + i + ".png")));
		}

		walls_images = new ArrayList<Image>();
		walls_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader().getResource("it/batteringvalhalla/assets/wall/wall.png")));
		walls_images.add(ImageIO.read(
				ResourcesLoader.class.getClassLoader().getResource("it/batteringvalhalla/assets/wall/wall2.png")));
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
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_option.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/editor.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_editor.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/exit.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_exit.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		mainmenu_images.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/arena/background.png")));

		mainmenu_images.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/gui/background-ui.png")));
	}

	public static synchronized void loadIcons() throws IOException {
		String base_path = "gui/menu/icon/";
		// play icons
		images.put("play", read(base_path, "play.png"));
		images.put("play_hover", read(base_path, "play_hover.png"));
		images.put("play_selected", read(base_path, "play_selected.png"));
		// online icons
		images.put("online", read(base_path, "online.png"));
		images.put("online_hover", read(base_path, "online_hover.png"));
		images.put("online_selected", read(base_path, "online_selected.png"));
		// editor icons
		images.put("editor", read(base_path, "editor.png"));
		images.put("editor_hover", read(base_path, "editor_hover.png"));
		images.put("editor_selected", read(base_path, "editor_selected.png"));
		// options icons
		images.put("options", read(base_path, "options.png"));
		images.put("options_hover", read(base_path, "options_hover.png"));
		images.put("options_selected", read(base_path, "options_selected.png"));
		// resume icons
		images.put("resume", read(base_path, "resume.png"));
		images.put("resume_hover", read(base_path, "resume_hover.png"));
		images.put("resume_selected", read(base_path, "resume_selected.png"));
		// host icons
		images.put("host", read(base_path, "host.png"));
		images.put("host_hover", read(base_path, "host_hover.png"));
		images.put("host_selected", read(base_path, "host_selected.png"));
		// join icons
		images.put("join", read(base_path, "join.png"));
		images.put("join_hover", read(base_path, "join_hover.png"));
		images.put("join_selected", read(base_path, "join_selected.png"));
		// exit icons
		images.put("exit", read(base_path, "exit.png"));
		images.put("exit_hover", read(base_path, "exit_hover.png"));
		images.put("exit_selected", read(base_path, "exit_selected.png"));
		// exit big icons
		images.put("exit_big", read(base_path, "exit_big.png"));
		images.put("exit_big_hover", read(base_path, "exit_big_hover.png"));
		images.put("exit_big_selected", read(base_path, "exit_big_selected.png"));
		// save big icons
		images.put("save", read(base_path, "save.png"));
		images.put("save_hover", read(base_path, "save_hover.png"));
		images.put("save_selected", read(base_path, "save_selected.png"));
		// load big icons
		images.put("load", read(base_path, "load.png"));
		images.put("load_hover", read(base_path, "load_hover.png"));
		images.put("load_selected", read(base_path, "load_selected.png"));

	}

	public static synchronized void loadEditorIcons() throws IOException {
		String base_path = "gui/menu/icon/";
		// player editor icons
		images.put("player_editor", read(base_path, "player_editor.png"));
		images.put("player_editor_hover", read(base_path, "player_editor_hover.png"));
		images.put("player_editor_selected", read(base_path, "player_editor_selected.png"));
		// map editor icons
		images.put("map_editor", read(base_path, "map_editor.png"));
		images.put("map_editor_hover", read(base_path, "map_editor_hover.png"));
		images.put("map_editor_selected", read(base_path, "map_editor_selected.png"));

	}

	public static synchronized void loadRoundIcons() throws IOException {
		String base_path = "gui/menu/icon/round/";
		// back icons
		images.put("back", read(base_path, "back.png"));
		images.put("back_hover", read(base_path, "back_hover.png"));
		// forward icons
		images.put("forward", read(base_path, "forward.png"));
		images.put("forward_hover", read(base_path, "forward_hover.png"));
		// down icons
		// scaled 60x60
		images.put("down", read(base_path, "down.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		images.put("down_hover", read(base_path, "down_hover.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		// sound on icons
		images.put("sound_on", read(base_path, "sound_on.png"));
		images.put("sound_on_hover", read(base_path, "sound_on_hover.png"));
		// sound off icons
		images.put("sound_of", read(base_path, "sound_off.png"));
		images.put("sound_off_hover", read(base_path, "sound_off_hover.png"));
		// pause icons
		// scaled 60x60
		images.put("pause", read(base_path, "pause.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		images.put("pause_hover", read(base_path, "pause_hover.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		// exit icons
		// scaled 60x60
		images.put("exit_round", read(base_path, "exit.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		images.put("exit_round_hover", read(base_path, "exit_hover.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		// scaled 100x100
		images.put("exit_red", read(base_path, "exit_red.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		images.put("exit_red_hover",
				read(base_path, "exit_red_hover.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		// scaled 100x100
		images.put("exit_blue", read(base_path, "exit_blue.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		images.put("exit_blue_hover",
				read(base_path, "exit_blue_hover.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		// confirm icons
		// scaled 100x100
		images.put("confirm_blue", read(base_path, "confirm_blue.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		images.put("confirm_blue_hover",
				read(base_path, "confirm_blue_hover.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		// scaled 100x100
		images.put("confirm_red", read(base_path, "confirm_red.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		images.put("confirm_red_hover",
				read(base_path, "confirm_red_hover.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		// restart icons
		images.put("restart", read(base_path, "restart.png"));
		images.put("restart_hover", read(base_path, "restart_hover.png"));
	}

	public static synchronized void loadBackgrounds() throws IOException {
		String base_path = "gui/menu/background/";
		// 600 x 600
		images.put("background_6x6", read(base_path, "background_600x600.png"));
		// 600 x 400
		images.put("background_6x4", read(base_path, "background_600x400.png"));
		// 512 x 256
		images.put("background_5x2", read(base_path, "background_512x256.png"));

	}

	public static synchronized BufferedImage read(String path, String file) throws IOException {
		return ImageIO
				.read(ResourcesLoader.class.getClassLoader().getResource("it/batteringvalhalla/assets/" + path + file));
	}

	public static synchronized void loadOptionMenuImages() throws IOException {
		optionmenu_images = new ArrayList<Image>();

		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/on.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_on.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/off.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_off.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/background/option_background.png"))
				.getScaledInstance(768, 640, Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/back.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		optionmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_back.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		optionmenu_images.add(ImageIO.read(ResourcesLoader.class.getClassLoader()
				.getResource("it/batteringvalhalla/assets/gui/menu/all_keys.png")));

	}

	public static synchronized void loadExitMenuImages() throws IOException {
		exitmenu_images = new ArrayList<Image>();

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/background/background_512x256.png"))
				.getScaledInstance(512, 256, Image.SCALE_SMOOTH));
		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/yes_red.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/no_blue.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_yes_red.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_no_blue.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/yes_blue.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_yes_blue.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/no_red.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		exitmenu_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_no_red.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

	}

	public static synchronized void loadScoreBoardImages() throws IOException {
		scoreboard_images = new ArrayList<Image>();

		scoreboard_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/restart.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		scoreboard_images.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_restart.png"))
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

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

	public static synchronized void loadHeaders() throws IOException {
		String base_path = "gui/menu/header/";
		// game header
		header = read("gui/", "header.png");
		// editors menu header
		images.put("editors_header", read(base_path, "editors_header.png"));
		// exit menu header
		images.put("exit_header", read(base_path, "exit_header.png"));
		// userfield header
		images.put("userfield_header", read(base_path, "userfield_header.png"));
		// arcade header
		images.put("arcade_header", read(base_path, "arcade_header.png"));
		// maplist header
		images.put("maplist_header", read(base_path, "maplist_header.png"));
		// pause header
		images.put("pause_header", read(base_path, "pause_header.png"));
		// game header
		images.put("game_header", read(base_path, "game_header.png"));
		// online header
		images.put("online_header", read(base_path, "online_header.png"));
		// host header
		images.put("host_header", read(base_path, "host_header.png"));
		// join header
		images.put("join_header", read(base_path, "join_header.png"));
	}

	public static synchronized void loadEditorImages() throws IOException {
		imageBust = new ArrayList<Image>();
		imageGoat = new ArrayList<Image>();
		imageHead = new ArrayList<Image>();
		leftArrow = new ArrayList<Image>();
		rightArrow = new ArrayList<Image>();
		Sprite tmp;

		// for (int i = 0; i <ResourcesLoader.actor_head.size(); i++) {
		// tmp=new Sprite(ResourcesLoader.actor_head.get(i),
		// ResourcesLoader.actor_head.get(i).getWidth(null),
		// ResourcesLoader.actor_head.get(i).getHeight(null), 115, 94, 1, 1, 0,
		// 0, 0);
		// tmp.update(Direction.est);
		// imageHead.add(tmp.getFrame());
		// }

		// for (int i = 0; i < ResourcesLoader.actor_head.size(); i++) {
		// tmp = new Sprite(ResourcesLoader.actor_head.get(i), 117, 88, 1, 1, 0,
		// 0, 0);
		// tmp.update(Direction.est);
		// imageHead.add(tmp.getFrame());
		//
		// }

		// for (int i = 0; i < ResourcesLoader.actor_body.size(); i++) {
		// tmp = new Sprite(ResourcesLoader.actor_body.get(i), 103, 76, 1, 3, 0,
		// 0, 0);
		// tmp.update(Direction.est);
		//
		//
		// imageBust.add(tmp.getFrame());
		// }

		imageHead.addAll(ResourcesLoader.actor_head);
		imageBust.addAll(actor_body);

		for (int i = 0; i < ResourcesLoader.actor_mount.size(); i++) {
			tmp = new Sprite(ResourcesLoader.actor_mount.get(i), 117, 88, 1, 1, 0, 0, 0);
			tmp.update(Direction.est);
			imageGoat.add(tmp.getFrame());

		}

		leftArrow.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/back.png"))
				.getScaledInstance(123, 133, Image.SCALE_SMOOTH));

		leftArrow.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_back.png"))
				.getScaledInstance(123, 133, Image.SCALE_SMOOTH));

		rightArrow.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/forward.png"))
				.getScaledInstance(123, 133, Image.SCALE_SMOOTH));

		rightArrow.add(ImageIO
				.read(ResourcesLoader.class.getClassLoader()
						.getResource("it/batteringvalhalla/assets/gui/menu/icon/hover/h_forward.png"))
				.getScaledInstance(123, 133, Image.SCALE_SMOOTH));
	}
}
