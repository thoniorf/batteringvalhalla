package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamegui.GamePanel;

import java.awt.Rectangle;

public class GameManager {

	GameWorld world;
	GamePanel panel;
	CollisionHandler collisiondander;

	public GameManager(GamePanel panel) {
		this.panel = panel;
		this.world = new GameWorld();
		this.collisiondander = new CollisionHandler(new Rectangle(1024, 768));
	}

	public GameWorld getWorld() {
		return world;
	}

	public void init() {
		world.setState(1);
		world.newMatch(1);
	}

	private void nextMatch() {
		try {
			Thread.sleep(1470);
			world.setState(1);
			world.nextMatch();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void endGame() {
		if (panel.paintRestartPrompt().equals(1)) {
			panel.resetInput();
			world.reset();
			init();
		} else
			System.exit(0);
	}

	// TODO implements game state in replacements of current game logic
	public void run() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				panel.requestFocus();
				while (world.getState() != 4) {
					while (world.getState().equals(1)) {
						world.update();
						collisiondander.checkCollisions(world.getObjects());
						panel.repaint();
						panel.getInput();
						try {
							Thread.sleep(30);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if (world.getState().equals(3))
						nextMatch();
					else if (world.getState().equals(4))
						endGame();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			};
		}.start();

	}
}
