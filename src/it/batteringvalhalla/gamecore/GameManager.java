package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamegui.GamePanel;

import java.awt.Rectangle;

public class GameManager {

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

	public void run() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				panel.requestFocus();
				try {
					if (world.getNext())
						Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while (true) {
					while (status == 1) {
						panel.getInput();
						world.update();
						collisiondander.checkCollisions(world.getObjects());
						panel.repaint();
						try {
							if (world.getNext())
								Thread.sleep(970);
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
			};
		}.start();

	}

	public void init() {
		status = 1;
	}
}
