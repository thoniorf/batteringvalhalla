package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.input.InputHandler;
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
		if (manager.getStatus() == 1) {
			if (keys.get(0)) {
				moving = true;
				world.getPlayer().setDirection(Direction.nord);
			}
			if (keys.get(1)) {
				moving = true;
				world.getPlayer().setDirection(Direction.sud);
			}
			if (keys.get(2)) {
				moving = true;
				world.getPlayer().setDirection(Direction.est);
			}
			if (keys.get(3)) {
				moving = true;
				world.getPlayer().setDirection(Direction.ovest);
			}
		}
		if (keys.get(4))
			if (manager.getStatus() == 1) {
				manager.setStatus(0);
			} else {
				manager.setStatus(1);
			}
		if (!moving)
			world.getPlayer().setDirection(Direction.stop);
	}
}
