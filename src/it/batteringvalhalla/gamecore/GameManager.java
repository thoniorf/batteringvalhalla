package it.batteringvalhalla.gamecore;

import it.batteringvalhalla.gamegui.GamePanel;

public class GameManager implements Runnable {

	GameWorld world;
	GamePanel panel;
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
		world = new GameWorld();
		status = 1;
	}

	@Override
	public void run() {
		while (status == 1) {
			world.update();
			panel.repaint();
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
