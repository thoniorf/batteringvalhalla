package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.AbstractGameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

public class GameWorld {
	List<AbstractGameObject> objects;
	Actor player;

	public Actor getPlayer() {
		return player;
	}

	Arena arena;
	Integer enemies;
	Integer match;
	Boolean next;

	public GameWorld() {
		firstMatch();

	}

	public void firstMatch() {
		next = new Boolean(true);
		enemies = new Integer(0);
		match = new Integer(0);
		arena = new Arena();
		objects = new CopyOnWriteArrayList<AbstractGameObject>();
		player = new Actor(200, 300);
		objects.add(player);
		altPlayer();
		startMatch(1, 1);
	}

	public Boolean getNext() {
		return next;
	}

	public List<AbstractGameObject> getObjects() {
		return objects;
	}

	public void startMatch(Integer enemies, Integer match) {
		this.match = match;
		this.enemies = enemies;
		for (int i = 0; i < enemies; i++) {
			objects.add(new Actor(400, 500));
		}
	}

	public void nextMatch() {
		altPlayer();
		next = true;
		this.match += 1;
		startMatch(1, match);
	}

	public void endGame() {
		player.setScore(match);
		switch (JOptionPane.showConfirmDialog(null, "You survived to " + match
				+ " matchs. " + "Retry ?", "Game Over",
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

	public void paint(Graphics g) {
		arena.paint(g);
		g.setColor(Color.PINK);
		g.setFont(new Font("Serif", Font.ITALIC, 30));
		g.drawString("Live:" + getPlayer().getLive(), 30, 70);
		for (AbstractGameObject obj : objects) {
			if (((Actor) obj).getLive() != 0) {
				obj.paint(g);
			}
		}
		if (next) {
			g.setColor(Color.BLACK);
			// g.setFont(new Font("Serif", Font.BOLD, 144));
			// g.drawString("Match:" + match.toString(), 150, 150);
			next = false;
		}

	}

	public void update() {
		if (!arena.getEdge().contains(player.getX(), player.getY())) {
			endGame();
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

	private void altPlayer() {
		player.setSpeedX(0f);
		player.setSpeedX(0f);
	}

	public void zOrder() {
		objects.sort(null);
	}
}
