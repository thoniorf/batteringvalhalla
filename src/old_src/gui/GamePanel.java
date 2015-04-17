package it.thoniorf.testgame.gui;

import it.thoniorf.testgame.GameWorld;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private Toolkit tk;
	private GameWorld world;
	private GameComponent component;
	private ArrayList<GameComponent> ecomponent;

	// private InputHandler keyboard;

	public GamePanel(GameWorld world) {
		super();
		this.setFocusTraversalKeysEnabled(true);
		this.world = world;
		tk = Toolkit.getDefaultToolkit();

		component = new GameComponent(tk, world.getPlayer().getSprite(), world
				.getPlayer().getX(), world.getPlayer().getY());
		ecomponent = new ArrayList<GameComponent>();
		for (int i = 0; i < 5; i++) {
			ecomponent.add(new GameComponent(tk, world.getEnemies().get(i)
					.getSprite(), world.getEnemies().get(i).getX(), world
					.getEnemies().get(i).getY()));
			ecomponent.get(i).setVisible(true);
			this.add(ecomponent.get(i));
		}
		component.setVisible(true);
		this.add(component);
		// this.keyboard = keyboard;
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				world.getPlayer().setHor(0);
				world.getPlayer().setVer(0);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_LEFT == e.getKeyCode()) {
					world.getPlayer().setHor(-1);
				}
				if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
					world.getPlayer().setHor(1);
				}
				if (KeyEvent.VK_DOWN == e.getKeyCode()) {
					world.getPlayer().setVer(1);
				}
				if (KeyEvent.VK_UP == e.getKeyCode()) {
					world.getPlayer().setVer(-1);
				}

			}
		});

		this.setFocusable(true);

	}

	public void updateGUI() {
		component.setCords(world.getPlayer().getX(), world.getPlayer().getY());
		for (int i = 0; i < 5; i++)
			ecomponent.get(i).setCords(world.getEnemies().get(i).getX(),
					world.getEnemies().get(i).getY());

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawPolygon(world.getBorder());

	}
}
