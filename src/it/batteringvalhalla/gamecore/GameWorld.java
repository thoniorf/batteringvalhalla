package it.batteringvalhalla.gamecore;

import java.awt.Graphics2D;
import java.util.ArrayList;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.Entity;
import it.batteringvalhalla.gamecore.object.actor.Enemy;
import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;

public class GameWorld {
	private static GameWorld world;
	private static ArrayList<Entity> objects;
	private static Arena arena;
	private static Integer max_enemy;
	private static Integer enemies;
	private static Player player;
	// friction time in ms
	private static Integer freq_friction = 250;
	// custom level
	private static String customLevel = "";

	public static String getCustomLevel() {
		return customLevel;
	}

	public static void setCustomLevel(String levelname) {
		customLevel = levelname;
	}

	private static void drawOrder() {
		objects.sort(null);
	}

	public static Integer getMax_enemy() {
		return max_enemy;
	}

	public static ArrayList<Entity> getObjects() {
		return objects;
	}

	public static Player getPlayer() {
		return player;
	}

	public static Integer getFreq_friction() {
		return freq_friction;
	}

	public static void setFreq_friction(Integer freq_friction) {
		GameWorld.freq_friction = freq_friction;
	}

	public static GameWorld getWorld() {
		if (world == null) {
			world = new GameWorld();
		}
		return world;
	}

	public static void paint(Graphics2D g) {
		arena.paint(g);
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).getAlive()) {
				objects.get(i).paint(g);
			}

		}
	}

	public static void makeLevel(int n_enemies) {
		// freq_friction = ManagerFilePlayer.getAttritoMap(customLevel);
		// Initialize arena
		arena = new Arena(ResourcesLoader.mainmenu_images.get(9));
		// get spawn point
		arena.setSpawn(ManagerFilePlayer.getSpawnInTheMap(customLevel, arena.getShape().getBounds().x,
				arena.getShape().getBounds().y));
		// level max enemies
		setMax_enemy(n_enemies);
		// level enemies number
		enemies = max_enemy;
		// object list
		objects = new ArrayList<Entity>();
		// spawn Player
		player = new Player(arena.getSpawn().get(0));
		Player.setScore(GameManager.getRound());
		objects.add(player);
		// spawn enemies
		for (int i = 0; i < enemies; i++) {
			objects.add(new Enemy(arena.getSpawn().get(i + 1)));
			// ((Enemy) objects.get(i + 1)).setStrategy(new IAFocus((Enemy)
			// objects.get(i + 1), arena, objects));
		}
		ArrayList<VerySquareWall> walls = (ArrayList<VerySquareWall>) ManagerFilePlayer.getWallsInTheMap(customLevel,
				arena.getShape().getBounds().x, arena.getShape().getBounds().y);
		for (int i = 0; i < walls.size(); i++) {
			objects.add(walls.get(i));

		}
	}

	public static void setMax_enemy(Integer max_enemies) {
		if (max_enemies > arena.getSpawn().size()) {
			max_enemy = arena.getSpawn().size() - 1;
		} else {
			max_enemy = max_enemies;
		}
	}

	public static void update() {
		drawOrder();
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).getAlive()) {
				objects.get(i).update();
				if (!arena.getShape().contains(objects.get(i).getOrigin())) {
					objects.get(i).setAlive(Boolean.FALSE);
					// game over
					if (objects.get(i) instanceof Player) {
						GameManager.setState(State.Over);
						return;
					} else if (objects.get(i) instanceof Enemy) {
						enemies += -1;
					}
				}
			}
		}
		// no more enemies
		if (enemies == 0) {
			GameManager.setState(State.Next);
			return;
		}

	}

	private GameWorld() {
	}
}
