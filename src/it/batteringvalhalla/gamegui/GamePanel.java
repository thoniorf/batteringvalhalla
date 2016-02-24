package it.batteringvalhalla.gamegui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.input.PlayerControls;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 999L;

	public GamePanel() {
		super();
		this.setOpaque(false);
		this.setFocusable(true);
		this.addKeyListener(new PlayerControls());
		this.setBounds(0, 0, GameFrame.size.width, GameFrame.size.height);
		requestFocusInWindow();
		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// paint World
		GameWorld.paint(g2d);
	}
}
