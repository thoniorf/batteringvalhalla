package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.input.InputHandler;
import it.batteringvalhalla.gamecore.object.actor.Actor;
import it.batteringvalhalla.gamecore.object.actor.Direction;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1568479702061945112L;
	GameFrame frame;
	GameWorld world;
	GameManager manager;
	InputHandler inputkey;

	public GamePanel(GameFrame frame) {
		super();
		this.frame = frame;
		init();
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(1024, 768));
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(true);
		this.setVisible(true);
		inputkey = new InputHandler();

		// addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// switch (e.getKeyCode()) {
		// case KeyEvent.VK_UP:
		// ((Actor) world.getObjects().get(0))
		// .setDirection(Direction.nord);
		// break;
		//
		// case KeyEvent.VK_DOWN:
		// ((Actor) world.getObjects().get(0))
		// .setDirection(Direction.sud);
		//
		// break;
		// case KeyEvent.VK_RIGHT:
		// ((Actor) world.getObjects().get(0))
		// .setDirection(Direction.est);
		//
		// break;
		// case KeyEvent.VK_LEFT:
		// ((Actor) world.getObjects().get(0))
		// .setDirection(Direction.ovest);
		//
		// break;
		//
		// case KeyEvent.VK_ESCAPE:
		// if (manager.getStatus() == 1)
		// manager.setStatus(0);
		// else
		// manager.setStatus(1);
		// break;
		// default:
		//
		// break;
		//
		// }
		//
		// }
		//
		// @Override
		// public void keyReleased(KeyEvent e) {
		// ((Actor) world.getObjects().get(0))
		// .setDirection(Direction.stop);
		// }
		// });

		addKeyListener(inputkey);

	}

	private void init() {
		manager = new GameManager(this);
		world = manager.getWorld();
		manager.init();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		world.paint(g);
	}

	public GameManager getManager() {
		return manager;
	}

	public void getInput() {
		List<Boolean> keys = inputkey.getKeys();
		Boolean moving = new Boolean(false);
		if (keys.get(0)) {
			moving = true;
			((Actor) world.getObjects().get(0)).setDirection(Direction.nord);
		}
		if (keys.get(1)) {
			moving = true;
			((Actor) world.getObjects().get(0)).setDirection(Direction.sud);
		}
		if (keys.get(2)) {
			moving = true;
			((Actor) world.getObjects().get(0)).setDirection(Direction.est);
		}
		if (keys.get(3)) {
			moving = true;
			((Actor) world.getObjects().get(0)).setDirection(Direction.ovest);
		}
		if (keys.get(4))
			if (manager.getStatus() == 1)
				manager.setStatus(0);
			else
				manager.setStatus(1);
		if (!moving)
			((Actor) world.getObjects().get(0)).setDirection(Direction.stop);
	}
}
