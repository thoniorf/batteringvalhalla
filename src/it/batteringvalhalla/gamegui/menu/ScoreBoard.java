package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.sound.FileSound;
import it.batteringvalhalla.gamegui.sound.Sound;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javafx.scene.shape.Circle;

import javax.swing.JPanel;

public class ScoreBoard extends JPanel {

	private static final long serialVersionUID = -8054712688033068639L;
	GameFrame gameframe;
	Circle restart_circle;
	Circle no_circle;
	FileSound f;
	int screenh = 768;

	public ScoreBoard(GameFrame gameFrame) {
		super(null);
		this.gameframe = gameFrame;
		this.f = gameframe.getF();
		this.mediaLoader();
		this.listenerLoader();
	}

	public void showScoreboard() {

	}

	private void loadScoreboard() {

	}

	public void addScore(int score) {

	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawImage(ResourcesLoader.scoreboard_images.get(0), 182,
				screenh - 642 - 65, null);
		g.drawImage(ResourcesLoader.scoreboard_images.get(1), 312,
				screenh - 165 - 49, null);
		g.drawImage(ResourcesLoader.exitmenu_images.get(2), 584,
				screenh - 165 - 49, null);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 96));
		g.drawString("Scoreboard", 320, screenh - 567 - 96 / 3);
	}

	private void mediaLoader() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		ge.registerFont(ResourcesLoader.gothic);

		restart_circle = new Circle();
		restart_circle.setCenterX(312 + ResourcesLoader.scoreboard_images
				.get(1).getWidth(this) / 2);
		restart_circle.setCenterY((screenh - 165 - 33)
				+ ResourcesLoader.scoreboard_images.get(1).getHeight(this) / 2);
		restart_circle.setRadius(ResourcesLoader.scoreboard_images.get(1)
				.getHeight(this) / 2);

		no_circle = new Circle();
		no_circle.setCenterX(584 + ResourcesLoader.exitmenu_images.get(2)
				.getWidth(this) / 2);
		no_circle.setCenterY((screenh - 165 - 33)
				+ ResourcesLoader.exitmenu_images.get(2).getHeight(this) / 2);
		no_circle.setRadius(ResourcesLoader.exitmenu_images.get(2).getHeight(
				this) / 2);
	}

	private void listenerLoader() {
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				switch (listener(e.getX(), e.getY())) {
				case 1:
					try {
						if ((f.read()).equals("0")) {
							Sound.button.play();
						}
						gameframe.gameStart();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					break;
				case 2:
					if ((f.read()).equals("0")) {
						Sound.button.play();
					}
					gameframe.menuStart();
					break;
				}
				// repaint();
			}
		});
	}

	public int listener(int x, int y) {
		if (restart_circle.contains(x, y)) {
			return 1;
		} else if (no_circle.contains(x, y)) {
			return 2;
		}
		return 0;
	}

}
