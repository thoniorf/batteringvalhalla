package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.sound.FileSound;
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
import java.io.IOException;

import javafx.scene.shape.Circle;

import javax.swing.JPanel;

public class MainMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1819144638361253664L;
	GameFrame frame;
	Image play_draw;
	Circle play_circle;
	Image options_draw;
	Circle option_circle;
	Image exit_draw;
	Circle exit_circle;
	FileSound f;
	int screenh = 768;
	boolean enabled = false;

	public MainMenu(GameFrame frame) {
		this.enabled = true;
		this.frame = frame;
		this.f = frame.getF();

		try {
			this.mediaLoader();

		} catch (IOException e) {
			e.printStackTrace();
		}

		listenerLoader();

	}

	private void mediaLoader() throws IOException {
		play_draw = ResourcesLoader.mainmenu_images.get(0);
		options_draw = ResourcesLoader.mainmenu_images.get(2);
		exit_draw = ResourcesLoader.mainmenu_images.get(4);
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		ge.registerFont(ResourcesLoader.gothic);

		play_circle = new Circle();
		play_circle.setCenterX(438 + ResourcesLoader.mainmenu_images.get(0)
				.getWidth(this) / 2);
		play_circle.setCenterY((screenh - 270 - 212)
				+ ResourcesLoader.mainmenu_images.get(0).getHeight(this) / 2);
		play_circle.setRadius(ResourcesLoader.mainmenu_images.get(0).getHeight(
				this) / 2);

		option_circle = new Circle();
		option_circle.setCenterX(243 + ResourcesLoader.mainmenu_images.get(2)
				.getWidth(this) / 2);
		option_circle.setCenterY((screenh - 167 - 178)
				+ ResourcesLoader.mainmenu_images.get(2).getHeight(this) / 2);
		option_circle.setRadius(ResourcesLoader.mainmenu_images.get(2)
				.getHeight(this) / 2);

		exit_circle = new Circle();
		exit_circle.setCenterX(687 + ResourcesLoader.mainmenu_images.get(4)
				.getWidth(this) / 2);
		exit_circle.setCenterY((screenh - 84 - 149)
				+ ResourcesLoader.mainmenu_images.get(4).getHeight(this) / 2);
		exit_circle.setRadius(ResourcesLoader.mainmenu_images.get(4).getHeight(
				this) / 2);

	}

	private void listenerLoader() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				switch (listener(e.getX(), e.getY())) {
				case 1:
					try {
						if ((f.read()).equals("0")) {
							Sound.button.play();
						}
						frame.gameStart();
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					break;
				case 2:
					try {
						if ((f.read()).equals("0")) {
							Sound.button.play();
						}
						frame.opt();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				case 3:
					try {
						if ((f.read()).equals("0")) {
							Sound.button.play();
						}
						frame.exit();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				default:
					break;

				}
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.drawImage(play_draw, 438, screenh - 270 - 212, null);
		g.drawImage(options_draw, 243, screenh - 167 - 178, null);
		g.drawImage(exit_draw, 687, screenh - 84 - 149, null);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 144));
		g.drawString("Battering Valhalla", 39, screenh - 594);
	}

	public int listener(int x, int y) {
		if (play_circle.contains(x, y)) {
			return 1;
		} else if (option_circle.contains(x, y)) {
			return 2;
		} else if (exit_circle.contains(x, y)) {
			return 3;
		}
		return 0;
	}

	public void motionListener(int x, int y) {
		if (play_circle.contains(x, y)) {
			play_draw = ResourcesLoader.mainmenu_images.get(1);
		} else {
			play_draw = ResourcesLoader.mainmenu_images.get(0);
		}
		if (option_circle.contains(x, y)) {
			options_draw = ResourcesLoader.mainmenu_images.get(3);
		} else {
			options_draw = ResourcesLoader.mainmenu_images.get(2);
		}
		if (exit_circle.contains(x, y)) {
			exit_draw = ResourcesLoader.mainmenu_images.get(5);
		} else {
			exit_draw = ResourcesLoader.mainmenu_images.get(4);
		}
		repaint();
	}
}
