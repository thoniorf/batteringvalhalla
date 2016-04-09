package it.batteringvalhalla.gamecore;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.collision.CollisionHandler;
import it.batteringvalhalla.gamecore.input.PlayerControls;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.GamePanel;

public class GameManager implements Runnable {
	private final static int MAX_FPS = 33;
	private final static int MAX_FRAME_SKIP = 5;
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;
	private static GameManager manager;
	private static Integer round = new Integer(1);
	private static State state;
	private GamePanel viewport;

	public static void main(String[] args) {
		// game frame
		GameFrame frame = GameFrame.instance();
		// game world
		GameWorld.getWorld();
		// game manager
		GameManager.getManager();
		// options loader
		new ManagerFilePlayer();
		// loading menu
		frame.showLoading();
		frame.start();

	}

	public static GameManager getManager() {
		if (manager == null) {
			manager = new GameManager();
		}
		return manager;
	}

	public static Integer getRound() {
		return round;
	}

	public static State getState() {
		return state;
	}

	public static void setRound(Integer round) {
		GameManager.round = round;
	}

	public static void setState(State state) {
		GameManager.state = state;
	}

	public JPanel getViewport() {
		return viewport;
	}

	public void setViewport(JPanel viewport) {
		this.viewport = (GamePanel) viewport;
	}

	public GameManager() {
	}

	public static void getInput() {
		GameWorld.getPlayer().setMoveDirection(Direction.stop);
		if (PlayerControls.getKeys().get("W")[0] == 1) {
			GameWorld.getPlayer().setMoveDirection(Direction.nord);
		}
		if (PlayerControls.getKeys().get("A")[0] == 1) {
			GameWorld.getPlayer().setMoveDirection(Direction.ovest);
		}
		if (PlayerControls.getKeys().get("S")[0] == 1) {
			GameWorld.getPlayer().setMoveDirection(Direction.sud);
		}
		if (PlayerControls.getKeys().get("D")[0] == 1) {
			GameWorld.getPlayer().setMoveDirection(Direction.est);
		}
		if (PlayerControls.getKeys().get("SPACE")[0] == 1) {
			GameWorld.getPlayer().charge();
		}
	}

	@Override
	public void run() {
		try {
			if (viewport == null) {
				throw new NullPointerException("Viewport");
			}
			GameManager.setState(State.Run);
			GameManager.setRound(1);
			viewport.setScore(round.toString());
			// set times and frames for constant FPS
			long beginTime = 0; // the time when the cycle begun
			long timeDiff = 0; // the time it took for the cycle to execute
			int sleepTime = 0; // ms to sleep (if < 0 we're behind)
			int framesSkipped = 0; // number of frames being skipped
			// main loop
			while (!state.equals(State.Stop)) {
				viewport.requestFocus();
				while (state.equals(State.Run)) {
					// frame start time
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;
					// main cycle
					GameManager.getInput();
					GameWorld.update();
					CollisionHandler.check();
					viewport.setCharge(GameWorld.getPlayer().canCharge());
					viewport.repaint();
					// frame diff after cycle
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
						GameWorld.update();
						sleepTime += FRAME_PERIOD;
						framesSkipped++;
					}
				}
				if (state.equals(State.Next)) {
					GameManager.setRound(GameManager.getRound() + 1);
					viewport.setScore(round.toString());
					GameWorld.makeLevel(GameWorld.getMax_enemy() + 1);
					GameManager.setState(State.Run);
				} else if (state.equals(State.Over)) {
					GameFrame.instance().showScores();
					break;
				}
			}
		} catch (NullPointerException e) {
			System.err.println(e);
		}
	}

}
