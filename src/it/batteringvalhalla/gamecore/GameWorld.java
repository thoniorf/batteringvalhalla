package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.AbstractGameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GameWorld {
	ArrayList<AbstractGameObject> objects;
	Arena arena;
	Integer enemies;
	Integer match;
	Boolean next;

	public Boolean getNext() {
		return next;
	}

	public GameWorld() {
		firstMatch();

	}

	public void update() {
		if (!arena.getEdge().contains(objects.get(0).getX(),
				objects.get(0).getY())) {
			JOptionPane gameover = new JOptionPane("Game Over ",
					JOptionPane.QUESTION_MESSAGE);
			switch (gameover.showConfirmDialog(null, "Retry ?", "Game Over",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
			case JOptionPane.YES_OPTION:
				firstMatch();
				break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
				break;
			default:
				break;
			}
		}
		if (enemies == 0) {
			nextMatch();
		}
		for (AbstractGameObject obj : objects) {
			if (((Actor) obj).getLive() != 0) {
				obj.update();
				if (!arena.getEdge().contains(obj.getX(), obj.getY())) {
					((Actor) obj).setLive(0);
					enemies -= 1;
				}
			}
		}
	}

	public ArrayList<AbstractGameObject> getObjects() {
		return objects;
	}

	public void paint(Graphics g) {
		arena.paint(g);
		for (AbstractGameObject obj : objects) {
			if (((Actor) obj).getLive() != 0) {
				obj.paint(g);
			}
		}
		if (next) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Serif", Font.BOLD, 144));
			g.drawString("Match:" + match.toString(), 150, 150);
			next = false;
		}
	}

	public void nextMatch() {
		next = true;
		this.match += 1;
		init(1, match);
	}

	public void firstMatch() {
		next = new Boolean(true);
		enemies = new Integer(0);
		match = new Integer(0);
		arena = new Arena();
		objects = new ArrayList<AbstractGameObject>();
		objects.add(new Actor(200, 300));
		init(1, 1);
	}

	public void init(Integer enemies, Integer match) {
		this.match = match;
		this.enemies = enemies;
		for (int i = 0; i < enemies; i++) {
			objects.add(new Actor(400, 500));
		}
	}
}
