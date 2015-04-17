package it.thoniorf.testgame;

import it.thoniorf.collision.CollisionHandler;
import it.thoniorf.entity.GameObject;
import it.thoniorf.testgame.gui.GameFrame;

import java.awt.Rectangle;
import java.util.ArrayList;

public class GameManager {
	GameWorld world;
	GameFrame frame;
	CollisionHandler colcheck;

	public static void main(String[] args) {
		new GameManager().startGame();
	}

	private void startGame() {
		world = new GameWorld();
		colcheck = new CollisionHandler(new Rectangle(0, 0, 800, 600));
		// InputHandler keyboard = new InputHandler(world.getPlayer());
		frame = new GameFrame(world);

		while (true) {
			world.update();
			ArrayList<GameObject> objects = new ArrayList<GameObject>();
			for (int i = 0; i < world.getEnemies().size(); i++) {
				objects.add(world.getEnemies().get(i));
			}
			objects.add(world.getPlayer());
			colcheck.checkCollisions(objects);
			if (world.getGameStatus() == 0)
				frame.popUp();
			frame.update();

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
