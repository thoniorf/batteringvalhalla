package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.object.actor.Actor;
import it.batteringvalhalla.gamecore.object.actor.Direction;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameWorld world;

	public GamePanel(GameWorld world) {
		super();
		this.world = world;
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(1024, 768));
		this.setFocusable(true);
		this.setVisible(true);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					((Actor) world.getObjects().get(0))
							.setDirection(Direction.nord);
					System.out.println("up");

					break;

				case KeyEvent.VK_DOWN:
					((Actor) world.getObjects().get(0))
							.setDirection(Direction.sud);

					break;
				case KeyEvent.VK_RIGHT:
					((Actor) world.getObjects().get(0))
							.setDirection(Direction.est);

					break;
				case KeyEvent.VK_LEFT:
					((Actor) world.getObjects().get(0))
							.setDirection(Direction.ovest);

					break;

				default:

					break;

				}

			}

			@Override
			public void keyReleased(final KeyEvent e) {
				((Actor) world.getObjects().get(0))
						.setDirection(Direction.stop);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		world.paint(g);
	}
}
