package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.GameFrame;

import it.batteringvalhalla.gamegui.sound.Sound;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javafx.scene.shape.Circle;

import javax.swing.JPanel;

public class ExitMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1542197631298382722L;
	GameFrame gameframe;
	Circle yes_circle;
	Image yes_draw;
	Circle no_circle;
	Image no_draw;
	
	int screenh = 768;

	public ExitMenu(GameFrame gameFrame) {
		super();
		this.gameframe = gameFrame;
		
		this.mediaLoader();
		this.listenerLoader();
	}

	private void mediaLoader() {
		yes_draw = ResourcesLoader.exitmenu_images.get(1);
		no_draw = ResourcesLoader.exitmenu_images.get(2);
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		ge.registerFont(ResourcesLoader.gothic);

		yes_circle = new Circle();
		yes_circle.setCenterX(312 + ResourcesLoader.exitmenu_images.get(1)
				.getWidth(this) / 2);
		yes_circle.setCenterY((screenh - 165 - 133)
				+ ResourcesLoader.exitmenu_images.get(1).getHeight(this) / 2);
		yes_circle.setRadius(ResourcesLoader.exitmenu_images.get(1).getHeight(
				this) / 2);

		no_circle = new Circle();
		no_circle.setCenterX(584 + ResourcesLoader.exitmenu_images.get(2)
				.getWidth(this) / 2);
		no_circle.setCenterY((screenh - 165 - 133)
				+ ResourcesLoader.exitmenu_images.get(2).getHeight(this) / 2);
		no_circle.setRadius(ResourcesLoader.exitmenu_images.get(2).getHeight(
				this) / 2);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawImage(ResourcesLoader.exitmenu_images.get(0), 182,
				screenh - 309 - 140, null);
		g.drawImage(yes_draw, 312, screenh - 165 - 133, null);
		g.drawImage(no_draw, 584, screenh - 165 - 133, null);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 96));
		g.drawString("Are you sure ?", 236, screenh - 315 - 96 / 3);

	}

	private void listenerLoader() {
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				switch (listener(e.getX(), e.getY())) {
				case 1:
					if (ManagerFilePlayer.soundOn()) {
						Sound.button.play();
					}
					System.exit(0);
					break;
				case 2:
					if (ManagerFilePlayer.soundOn()) {
						Sound.button.play();
					}
					gameframe.menuStart();
					break;
				}
				// repaint();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				motionListener(e.getX(), e.getY());

			}
		});

	}

	public int listener(int x, int y) {
		if (yes_circle.contains(x, y)) {
			return 1;
		} else if (no_circle.contains(x, y)) {
			return 2;
		}
		return 0;
	}

	public void motionListener(int x, int y) {
		if (yes_circle.contains(x, y)) {
			yes_draw = ResourcesLoader.exitmenu_images.get(3);
		} else {
			yes_draw = ResourcesLoader.exitmenu_images.get(1);
		}

		if (no_circle.contains(x, y)) {
			no_draw = ResourcesLoader.exitmenu_images.get(4);
		} else {
			no_draw = ResourcesLoader.exitmenu_images.get(2);
		}
		repaint();
	}

}
