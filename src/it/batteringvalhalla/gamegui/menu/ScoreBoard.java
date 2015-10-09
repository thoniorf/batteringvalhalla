package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.actor.Player;
import it.batteringvalhalla.gamecore.sqlite.ScoreFetch;
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
import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.shape.Circle;

import javax.swing.JPanel;

public class ScoreBoard extends JPanel {

	private static final long serialVersionUID = -8054712688033068639L;
	GameFrame gameframe;
	Circle restart_circle;
	Image restart_draw;
	Circle no_circle;
	Image no_draw;
	FileSound f;
	int screenh = 768;
	ArrayList<String> scores;

	public ScoreBoard(GameFrame gameFrame) {
		super(null);
		this.gameframe = gameFrame;
		this.f = gameframe.getF();
		this.mediaLoader();
		this.listenerLoader();
		this.loadScores();
	}

	private void loadScores() {
		scores = new ArrayList<String>();
		ScoreFetch scorefetch = new ScoreFetch();
		scorefetch.insertScore(Player.score, Player.username);
		scorefetch.execQuery(
				"Select * from scores order by match desc limit 5;", scores);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawImage(ResourcesLoader.scoreboard_images.get(0), 182,
				screenh - 642 - 65, null);
		g.drawImage(restart_draw, 312, screenh - 165 - 49, null);
		g.drawImage(no_draw, 584, screenh - 165 - 49, null);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 96));
		g.drawString("Scoreboard", 320, screenh - 567 - 96 / 3);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 64));
		Iterator<String> it = scores.iterator();
		int y = screenh - 512;
		while (it.hasNext()) {
			g.drawString(it.next(), 226, y);
			g.drawString(it.next(), 672, y);
			y += 72;
		}
	}

	private void mediaLoader() {

		restart_draw = ResourcesLoader.scoreboard_images.get(1);
		no_draw = ResourcesLoader.exitmenu_images.get(2);

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
					if ((f.read()).equals("0")) {
						Sound.battle.stop();
						Sound.menu.play();
					} else {
						Sound.menu.stop();
					}
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
		if (restart_circle.contains(x, y)) {
			return 1;
		} else if (no_circle.contains(x, y)) {
			return 2;
		}
		return 0;
	}

	public void motionListener(int x, int y) {
		if (restart_circle.contains(x, y)) {
			restart_draw = ResourcesLoader.scoreboard_images.get(2);
		} else {
			restart_draw = ResourcesLoader.scoreboard_images.get(1);
		}

		if (no_circle.contains(x, y)) {
			no_draw = ResourcesLoader.exitmenu_images.get(4);
		} else {
			no_draw = ResourcesLoader.exitmenu_images.get(2);
		}
		repaint();
	}

}
