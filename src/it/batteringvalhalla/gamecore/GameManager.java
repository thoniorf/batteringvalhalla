package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamegui.GamePanel;

import java.awt.Rectangle;

public class GameManager implements Runnable {

	GameWorld world;
	GamePanel panel;
	CollisionHandler collisiondander;
	int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public GameWorld getWorld() {
		return world;
	}

	public GameManager(GamePanel panel) {
		this.panel = panel;
		this.world = new GameWorld();
		this.collisiondander = new CollisionHandler(new Rectangle(1024, 768));
		init();
	}

	@Override
	public void run() {
		while (true) {
			while (status == 1) {
				world.update();
				collisiondander.checkCollisions(world.getObjects());
				panel.repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void init() {
		status = 1;
	}
}
