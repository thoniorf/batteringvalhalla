package it.thoniorf.testgame.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javafx.scene.shape.Circle;

public class MainMenu {

	Image play;
	Circle play_circle;
	Image options;
	Circle option_circle;
	Image exit;
	Circle exit_circle;
	Image normal;
	Image hover;
	Font font;
	int screenh = 768;
	public boolean enabled = false;

	public MainMenu(Font font, GameLayeredPane glp) {
		this.font = font;
		MediaTracker mt = new MediaTracker(glp);

		play = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource("/assets/gui/MainMenu/play.png"))
				.getScaledInstance(200, 212, java.awt.Image.SCALE_SMOOTH);
		options = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"/assets/gui/MainMenu/option.png"))
				.getScaledInstance(165, 178, java.awt.Image.SCALE_SMOOTH);
		exit = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource("/assets/gui/MainMenu/exit.png"))
				.getScaledInstance(139, 149, java.awt.Image.SCALE_SMOOTH);

		mt.addImage(play, 0);
		mt.addImage(options, 1);
		mt.addImage(exit, 2);

		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		play_circle = new Circle();
		play_circle.setCenterX(438 + play.getWidth(glp) / 2);
		play_circle.setCenterY((screenh - 270 - 212) + play.getHeight(glp) / 2);
		play_circle.setRadius(play.getHeight(glp) / 2);

		option_circle = new Circle();
		option_circle.setCenterX(243 + options.getWidth(glp) / 2);
		option_circle.setCenterY((screenh - 167 - 178) + options.getHeight(glp)
				/ 2);
		option_circle.setRadius(options.getHeight(glp) / 2);

		exit_circle = new Circle();
		exit_circle.setCenterX(687 + exit.getWidth(glp) / 2);
		exit_circle.setCenterY((screenh - 84 - 149) + exit.getHeight(glp) / 2);
		exit_circle.setRadius(exit.getHeight(glp) / 2);
	}

	protected void paint(Graphics g) {

		g.drawImage(play, 438, screenh - 270 - 212, null);
		g.drawImage(options, 243, screenh - 167 - 178, null);
		g.drawImage(exit, 687, screenh - 84 - 149, null);
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
}
