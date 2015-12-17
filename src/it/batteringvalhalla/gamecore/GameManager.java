package it.batteringvalhalla.gamecore;

import java.awt.Rectangle;

import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamecore.input.InputHandler;
import it.batteringvalhalla.gamecore.object.actor.Direction;
import it.batteringvalhalla.gamecore.object.actor.Player;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.GamePanel;
import it.batteringvalhalla.gamegui.menu.ExitMenu;

public class GameManager extends Thread {

	private static GameManager manager;

	private final static int MAX_FPS = 33;
	private final static int MAX_FRAME_SKIP = 5;
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;

	private long beginTime; // the time when the cycle begun
	private long timeDiff; // the time it took for the cycle to execute
	private int sleepTime; // ms to sleep (<0 if we're behind)
	private int framesSkipped; // number of frames being skipped

	private GameWorld world;
	private GamePanel panel;
	private CollisionHandler collisiondander;

	private GameManager() {
		this.world = GameWorld.instance();
		this.collisiondander = new CollisionHandler(new Rectangle(1024, 768));
	}

	public void getInput() {
		Boolean moving = Boolean.FALSE;
		if (GameWorld.getState().equals(1)) {
			if (InputHandler.getKeys()[0]) {
				moving = true;
				world.getPlayer().setDirection(Direction.nord);
			}
			if (InputHandler.getKeys()[1]) {
				moving = true;
				world.getPlayer().setDirection(Direction.sud);
			}
			if (InputHandler.getKeys()[2]) {
				moving = true;
				world.getPlayer().setDirection(Direction.est);
			}
			if (InputHandler.getKeys()[3]) {
				moving = true;
				world.getPlayer().setDirection(Direction.ovest);
			}
			if (InputHandler.getKeys()[5] && (InputHandler.getKeys()[0] || InputHandler.getKeys()[1]
					|| InputHandler.getKeys()[2] || InputHandler.getKeys()[3])) {
				moving = true;
				world.getPlayer().tryCharge();
			}
		}
		if (InputHandler.getKeys()[4]) {
			if (GameWorld.getState().equals(1)) {
				InputHandler.resetKeys();
				GameWorld.setState(2);
				GameFrame.instance().showExit();
				((ExitMenu) GameFrame.instance().getLayeredPane().getComponentsInLayer(3)[0])
						.setText("Return to Main Menu ?");
			}
		}

		if (!moving)
			world.getPlayer().setDirection(Direction.stop);
	}

	public GamePanel getPanel() {
		return panel;
	}

	private void nextMatch() {
		try {
			Thread.sleep(1500);
			world.setState(1);
			world.nextMatch();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		super.run();
		sleepTime = 0;
		panel.requestFocus();
		while (GameWorld.getState() != 4) {
			while (GameWorld.getState() == 1) {
				beginTime = System.currentTimeMillis();
				framesSkipped = 0;
				world.update();
				collisiondander.checkCollisions(world.getObjects());
				panel.repaint();
				this.getInput();
				timeDiff = System.currentTimeMillis() - beginTime;
				sleepTime = (int) (FRAME_PERIOD - timeDiff);

				if (sleepTime > 0) {
					// if sleepTime > 0 we're OK
					try {
						// send the thread to sleep for a short period
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIP) {
					world.update();
					sleepTime += FRAME_PERIOD;
					framesSkipped++;
				}
			}
			if (GameWorld.getState().equals(3)) {
				nextMatch();
			}
			this.getInput();
			System.out.println("running - " + GameWorld.getState());
		}
		Player.setScore(world.getMatch());
		GameFrame.instance().showScores();

	}

	public void setPanel(GamePanel panel) {
		this.panel = panel;
	}

	public static GameManager instance() {
		return manager;
	}

	public static void startNewManager(GamePanel panel) {
		manager = new GameManager();
		manager.setPanel(panel);
		GameWorld.instance().reset();
		GameWorld.setState(1);
		GameWorld.instance().newMatch(1);
		manager.start();
	}
}
