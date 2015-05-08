package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamegui.GamePanel;

import java.awt.Rectangle;

public class GameManager {

	GameWorld world;
	GamePanel panel;
	CollisionHandler collisiondander;
	int status;

	public GameManager(GamePanel panel) {
		this.panel = panel;
		this.world = new GameWorld();
		this.collisiondander = new CollisionHandler(new Rectangle(1024, 768));
		init();
	}

	public int getStatus() {
		return status;
	}

	public GameWorld getWorld() {
		return world;
	}

	public void init() {
		status = 1;
	}

	public void run() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				panel.requestFocus();
				try {
					if (world.getNext())
						Thread.sleep(1500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				while (true) {
					while (status == 1) {
						panel.getInput();
						world.update();
						world.zOrder();
						collisiondander.checkCollisions(world.getObjects());
						panel.repaint();
						try {
							if (world.getNext())
								Thread.sleep(1470);
							Thread.sleep(30);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					panel.getInput();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// TODO pause menu call
				}
			};
		}.start();

	}

	public void setStatus(int status) {
		this.status = status;
	}
}
