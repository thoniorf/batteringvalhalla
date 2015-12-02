package it.batteringvalhalla.gamegui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
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
	private JLabel playerANDscore;

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

		playerANDscore = new JLabel("Player 1    Match: 1");
		playerANDscore.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 36));
		playerANDscore.setAlignmentX(0.50f);
		// playerANDscore.setBounds(CenterComp.centerX(playerANDscore.getWidth()),
		// 12, playerANDscore.getWidth(),
		// playerANDscore.getHeight());
		add(playerANDscore);
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
		Boolean moving = new Boolean(false);
		if (world.getState() == 1) {
			if (inputkey.getKeys()[0]) {
				moving = true;
				world.getPlayer().setDirection(Direction.nord);
			}
			if (inputkey.getKeys()[1]) {
				moving = true;
				world.getPlayer().setDirection(Direction.sud);
			}
			if (inputkey.getKeys()[2]) {
				moving = true;
				world.getPlayer().setDirection(Direction.est);
			}
			if (inputkey.getKeys()[3]) {
				moving = true;
				world.getPlayer().setDirection(Direction.ovest);
			}
			if (inputkey.getKeys()[5]) {
				moving = true;
				world.getPlayer().tryCharge();
			}
		}
		if (inputkey.getKeys()[4])
			world.setState((world.getState() == 1 ? 2 : 1));

		if (!moving)
			world.getPlayer().setDirection(Direction.stop);
	}

	public void resetInput() {
		inputkey.resetKeys();
	}

	private void paintUI(Graphics g) {
		playerANDscore.setText(Player.getName() + "    Match: " + world.getMatch().toString());
	}
}
