package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.object.actor.Actor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	GameWorld world;

	public GamePanel(GameWorld world) {
		super();
		this.world = world;
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(1024, 768));
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setVisible(true);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (KeyEvent.VK_UP == e.getKeyCode()) {
					((Actor) world.getObjects().get(0)).setVer(-1);
				} else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
					((Actor) world.getObjects().get(0)).setVer(1);
				}
				if (KeyEvent.VK_LEFT == e.getKeyCode()) {
					((Actor) world.getObjects().get(0)).setHor(-1);
				} else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
					((Actor) world.getObjects().get(0)).setHor(1);
				}

			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		world.paint(g);
	}
}
