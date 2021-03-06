package it.batteringvalhalla.gamecore;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamecore.ia.IAFocus;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.Entity;
import it.batteringvalhalla.gamecore.object.actor.AbstractActor;
import it.batteringvalhalla.gamecore.object.actor.Enemy;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;
import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;

public class GameWorld {
	private static GameWorld world;
	private static List<Entity> objects;
	private static ArrayList<VerySquareWall> walls;
	private static Arena arena;
	private static Integer max_enemy;
	private static Integer enemies;
	private static AbstractActor player;
	// friction time in ms
	private static Integer freq_friction = 250;
	// custom level
	private static String levelName = "";

	public static Arena getArena() {
		return arena;
	}

	public static void setArena(Arena arena) {
		GameWorld.arena = arena;
	}

	public static ArrayList<VerySquareWall> getWalls() {
		return walls;
	}

	public static void setWalls(ArrayList<VerySquareWall> walls) {
		GameWorld.walls = walls;
	}

	public static Integer getFreq_friction() {
		return freq_friction;
	}

	public static void setFreq_friction(Integer freq_friction) {
		GameWorld.freq_friction = freq_friction;
	}

	public static String getLevelName() {
		return levelName;
	}

	public static void setLevelName(String name) {
		levelName = name;
	}

	public static Integer getMax_enemy() {
		return max_enemy;
	}

	public static void setMax_enemy(Integer max_enemies) {
		if (max_enemies > (arena.getSpawn().size() - 1)) {
			max_enemy = arena.getSpawn().size() - 1;
		} else {
			max_enemy = max_enemies;
		}
	}

	public static Integer getEnemies() {
		return enemies;
	}

	public static void setEnemies(Integer enemies) {
		GameWorld.enemies = enemies;
	}

	public static List<Entity> getObjects() {
		return objects;
	}

	public static void setObjects(List<Entity> list) {
		GameWorld.objects = list;
	}

	public static Player getPlayer() {
		return (Player) player;
	}

	public static void setPlayer(Player player) {
		GameWorld.player = player;
	}

	public static void setPlayer(OnlineCharacter character) {
		GameWorld.player = character;
	}

	public static GameWorld getWorld() {
		if (world == null) {
			world = new GameWorld();
		}
		return world;
	}

	public static void makeLevel(int n_enemies) {
		freq_friction = ManagerFilePlayer.getAttritoMap(levelName);
		// Initialize arena
		arena = new Arena(ResourcesLoader.mainmenu_images.get(9));
		// get spawn point
		arena.setSpawn(ManagerFilePlayer.getSpawnInTheMap(levelName, arena.getShape().getBounds().x,
				arena.getShape().getBounds().y));
		// level max enemies
		setMax_enemy(n_enemies);
		// level enemies number
		enemies = max_enemy;
		// object list
		objects = new ArrayList<Entity>();
		// spawn Player
		player = new Player(arena.getSpawn().get(0));
		objects.add(player);
		// spawn enemies
		for (int i = 0; i < enemies; i++) {
			objects.add(new Enemy(arena.getSpawn().get(i + 1)));
			((Enemy) objects.get(i + 1)).setStrategy(new IAFocus((Enemy) objects.get(i + 1), arena, objects));
		}
		walls = (ArrayList<VerySquareWall>) ManagerFilePlayer.getWallsInTheMap(levelName,
				arena.getShape().getBounds().x, arena.getShape().getBounds().y);
		for (int i = 0; i < walls.size(); i++) {
			objects.add(walls.get(i));

		}
		CollisionHandler.setObjects(new CopyOnWriteArrayList<Entity>(objects));
	}

	public static void paint(Graphics2D g) {
		arena.paint(g);
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).getAlive()) {
				objects.get(i).paint(g);
			}

		}
	}

	private static void drawOrder() {
		synchronized (objects) {
			objects.sort(null);
		}
	}

	public static void update() {
		drawOrder();
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).getAlive()) {
				objects.get(i).update();
				if (!arena.getShape().contains(objects.get(i).getOrigin())) {
					objects.get(i).setAlive(Boolean.FALSE);
					if (objects.get(i) instanceof OnlineCharacter) {
						if (((OnlineCharacter) objects.get(i)).getOnline_user().equals(Player.getUsername())) {
							GameManager.setState(State.Over);
						}
					} else if (objects.get(i) instanceof Player) {
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

	public GameWorld() {
	}

}
