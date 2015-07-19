package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamegui.GamePanel;

import java.awt.Rectangle;

public class GameManager {

	private final static int MAX_FPS = 33;
	private final static int MAX_FRAME_SKIP = 5;
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;

	private long beginTime; // the time when the cycle begun
	private long timeDiff; // the time it took for the cycle to execute
	private int sleepTime; // ms to sleep (<0 if we're behind)
	private int framesSkipped; // number of frames being skipped

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
			e.printStackTrace();
		}
	}

	private void endGame() {
		if (panel.paintRestartPrompt().equals(1)) {
			// panel.resetInput();
			world.reset();
			init();
		} else
			System.exit(0);
	}

	public void run() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				sleepTime = 0;
				panel.requestFocus();
				while (world.getState() != 4) {
					while (world.getState().equals(1)) {
						beginTime = System.currentTimeMillis();
						framesSkipped = 0;
						world.update();
						collisiondander.checkCollisions(world.getObjects());
						panel.repaint();
						panel.getInput();
						timeDiff = System.currentTimeMillis() - beginTime;
						sleepTime = (int) (FRAME_PERIOD - timeDiff);

						if (sleepTime > 0) {
							// if sleepTime > 0 we're OK
							try {
								// send the thread to sleep for a short period
								// very useful for battery saving
								Thread.sleep(sleepTime);
							} catch (InterruptedException e) {
							}

						}

						while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIP) {
							world.update();
							sleepTime += FRAME_PERIOD;
							framesSkipped++;

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
