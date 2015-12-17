package it.batteringvalhalla.gamegui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.input.InputHandler;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.actor.Player;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameWorld world;
	private JLabel playerANDscore;

	private int width = 1024;
	private int height = 768;

	public GamePanel() {
		super();
		this.setOpaque(false);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(true);
		this.addKeyListener(InputHandler.instance());
		InputHandler.resetKeys();
		this.setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
		this.world = GameWorld.instance();

		playerANDscore = new JLabel("Player 1    Match: 1");
		playerANDscore.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 36));
		playerANDscore.setAlignmentX(0.50f);
		add(playerANDscore);

		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		world.paint(g);
		refreshScoresLabel();
	}

	private void refreshScoresLabel() {
		playerANDscore.setText(Player.getName() + "    Match: " + world.getMatch().toString());
	}
}
