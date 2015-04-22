package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.object.GameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5688948969008792751L;
	GameWorld world;
	GameManager gamemanager;

	public GamePanel(GameFrame gameFrame) {
		super(null);
		gamemanager = new GameManager(this);
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
					for (GameObject a : world.getObjects()) {
						((Actor) a).setVer(2);
					}
					System.out.println("UP");
				} else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
					for (GameObject a : world.getObjects()) {
						((Actor) a).setVer(-2);
					}
					System.out.println("DOWN");
				}
				if (KeyEvent.VK_LEFT == e.getKeyCode()) {
					for (GameObject a : world.getObjects()) {
						((Actor) a).setHor(2);
					}
					System.out.println("LEFT");
				} else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
					for (GameObject a : world.getObjects()) {
						((Actor) a).setHor(-2);
					}
					System.out.println("RIGHT");
				}

			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gamemanager.getWorld().paint(g);

	}

	public void start() {
		gamemanager.setStatus(1);
		gamemanager.run();
	}

}
