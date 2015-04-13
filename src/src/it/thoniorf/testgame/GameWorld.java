package it.thoniorf.testgame;

import it.thoniorf.entity.Actor;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;

public class GameWorld {
	private Polygon border;
	private Actor player;
	private ArrayList<Actor> enemies;
	private Integer gamestatus;

	public GameWorld() {
		Random random = new Random();
		enemies = new ArrayList<Actor>();
		this.gamestatus = 1;
		this.setBorder();
		player = new Actor("Player.jpg");
		for (int i = 0; i < 5; i++) {
			enemies.add(new Actor("Enemy.jpg"));
			enemies.get(i).setX(random.nextInt(200) + 100);
			enemies.get(i).setY(random.nextInt(200) + 100);
		}

	}

	private void setBorder() {
		border = new Polygon();
		border.addPoint(300, 0);
		border.addPoint(88, 92);
		border.addPoint(0, 300);
		border.addPoint(88, 492);
		border.addPoint(300, 600);
		border.addPoint(512, 492);
		border.addPoint(600, 300);
		border.addPoint(512, 92);

	}

	public Polygon getBorder() {
		return border;
	}

	public Actor getPlayer() {
		return player;
	}

	public ArrayList<Actor> getEnemies() {
		return enemies;
	}

	public Integer getGameStatus() {
		return gamestatus;
	}

	public void update() {
		player.update(border);
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).update(border);
		if (player.getHealth() == 0)
			gamestatus = 0;

	}
}
