package it.batteringvalhalla.gamegui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.input.InputHandler;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.actor.Direction;
import it.batteringvalhalla.gamecore.object.actor.Player;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1568479702061945112L;
	GameFrame frame;
	GameWorld world;
	GameManager manager;
	InputHandler inputkey;

	private int width = 1024;
	private int height = 768;

	public GamePanel() {
		super();
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(1024, 768));
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(true);
		inputkey = new InputHandler();
		addKeyListener(inputkey);
		init();
		setVisible(true);

	}

	private void init() {
		manager = new GameManager(this);
		world = manager.getWorld();
		manager.init();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		world.paint(g);
		paintUI(g);
	}

	public GameManager getManager() {
		return manager;
	}

	public GameFrame getFrame() {
		return frame;
	}

	public void getInput() {
		List<Boolean> keys = inputkey.getKeys();
		Boolean moving = new Boolean(false);
		if (world.getState() == 1) {
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
			if (world.getState() == 1) {
				world.setState(2);
			} else {
				world.setState(1);
			}
		if (!moving)
			world.getPlayer().setDirection(Direction.stop);
	}

	public void resetInput() {
		inputkey.resetKeys();
	}

	private void paintUI(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 38));
		g.drawString(Player.getName(), 30, 70);
		g.drawString("Match:" + world.getMatch().toString(), 200, 70);
	}
}
