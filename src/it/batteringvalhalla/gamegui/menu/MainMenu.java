package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.GamePanel;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.JPanel;

//TODO exit menu & option menu

public class MainMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6276557663423646175L;
	GameFrame gameframe;
	Image play;
	Image play_odd;
	Image play_draw;
	Circle play_circle;
	Image options;
	Image options_odd;
	Image options_draw;
	Circle option_circle;
	Image exit;
	Image exit_odd;
	Image exit_draw;
	Circle exit_circle;
	Font font;
	int screenh = 768;
	boolean enabled = false;

	public MainMenu(GameFrame gameFrame) {
		super();
		this.gameframe = gameFrame;
		this.enabled = true;
		this.mediaLoader();
		this.listenerLoader();
		this.setFocusable(true);
		this.setVisible(true);

	}

	private void mediaLoader() {
		MediaTracker mt = new MediaTracker(this);

		play = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/menu/icon/play.png"))
				.getScaledInstance(200, 212, java.awt.Image.SCALE_SMOOTH);
		play_odd = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/menu/icon/hover/h_play.png"))
				.getScaledInstance(200, 212, java.awt.Image.SCALE_SMOOTH);
		options = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/menu/icon/option.png"))
				.getScaledInstance(165, 178, java.awt.Image.SCALE_SMOOTH);
		options_odd = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass()
								.getResource(
										"../../assets/gui/menu/icon/hover/h_option.png"))
				.getScaledInstance(165, 178, java.awt.Image.SCALE_SMOOTH);
		exit = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/menu/icon/exit.png"))
				.getScaledInstance(139, 149, java.awt.Image.SCALE_SMOOTH);
		exit_odd = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/menu/icon/hover/h_exit.png"))
				.getScaledInstance(139, 149, java.awt.Image.SCALE_SMOOTH);

		mt.addImage(play, 0);
		mt.addImage(options, 1);
		mt.addImage(exit, 2);
		mt.addImage(play_odd, 3);
		mt.addImage(options_odd, 4);
		mt.addImage(exit_odd, 5);
		play_draw = play;
		options_draw = options;
		exit_draw = exit;
		try {
			mt.waitForAll();
			this.font = Font.createFont(Font.TRUETYPE_FONT, getClass()
					.getResourceAsStream("../../assets/gui/fonts/Deutsch.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (InterruptedException | FontFormatException | IOException e) {
			e.printStackTrace();
		}

		play_circle = new Circle();
		play_circle.setCenterX(438 + play.getWidth(this) / 2);
		play_circle
				.setCenterY((screenh - 270 - 212) + play.getHeight(this) / 2);
		play_circle.setRadius(play.getHeight(this) / 2);

		option_circle = new Circle();
		option_circle.setCenterX(243 + options.getWidth(this) / 2);
		option_circle.setCenterY((screenh - 167 - 178)
				+ options.getHeight(this) / 2);
		option_circle.setRadius(options.getHeight(this) / 2);

		exit_circle = new Circle();
		exit_circle.setCenterX(687 + exit.getWidth(this) / 2);
		exit_circle.setCenterY((screenh - 84 - 149) + exit.getHeight(this) / 2);
		exit_circle.setRadius(exit.getHeight(this) / 2);
	}

	private void listenerLoader() {
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if (enabled)
					switch (listener(e.getX(), e.getY())) {
					case 1:
						gamemenu = new GamePanel(gameframe)
						break;
					case 2:
						// TODO option button event
						break;
					case 3:
						// exit.enabled = true;
						// enabled = false;
						break;
					}
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				if (enabled) {
					motionListener(e.getX(), e.getY(), 1);
				}
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.drawImage(play_draw, 438, screenh - 270 - 212, null);
		g.drawImage(options_draw, 243, screenh - 167 - 178, null);
		g.drawImage(exit_draw, 687, screenh - 84 - 149, null);
		g.setFont(new Font(font.getName(), font.getStyle(), 144));
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

	public void motionListener(int x, int y, int h) {
		if (play_circle.contains(x, y)) {
			play_draw = play_odd;
		} else {
			play_draw = play;
		}
		if (option_circle.contains(x, y)) {
			options_draw = options_odd;
		} else {
			options_draw = options;
		}
		if (exit_circle.contains(x, y)) {
			exit_draw = exit_odd;
		} else {
			exit_draw = exit;
		}
		repaint();
	}
}
