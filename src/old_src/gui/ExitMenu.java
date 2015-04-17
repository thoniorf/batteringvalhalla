package it.thoniorf.testgame.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javafx.scene.shape.Circle;

public class ExitMenu {
	Image background;
	Image yes;
	Circle yes_circle;
	Image no;
	Circle no_circle;
	Font font;
	int screenh = 768;
	public boolean enabled = false;

	public ExitMenu(Font font, GameLayeredPane glp) {
		this.font = font;
		MediaTracker mt = new MediaTracker(glp);

		background = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"/assets/gui/ExitMenu/background.png"))
				.getScaledInstance(678, 309, java.awt.Image.SCALE_SMOOTH);
		yes = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource("/assets/gui/ExitMenu/yes.png"))
				.getScaledInstance(123, 133, java.awt.Image.SCALE_SMOOTH);
		no = Toolkit
				.getDefaultToolkit()
				.getImage(getClass().getResource("/assets/gui/ExitMenu/no.png"))
				.getScaledInstance(123, 133, java.awt.Image.SCALE_SMOOTH);

		mt.addImage(background, 0);
		mt.addImage(yes, 1);
		mt.addImage(no, 2);

		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		yes_circle = new Circle();
		yes_circle.setCenterX(312 + yes.getWidth(glp) / 2);
		yes_circle.setCenterY((screenh - 165 - 133) + yes.getHeight(glp) / 2);
		yes_circle.setRadius(yes.getHeight(glp) / 2);

		no_circle = new Circle();
		no_circle.setCenterX(584 + no.getWidth(glp) / 2);
		no_circle.setCenterY((screenh - 165 - 133) + no.getHeight(glp) / 2);
		no_circle.setRadius(no.getHeight(glp) / 2);
	}

	protected void paint(Graphics g) {
		g.drawImage(background, 182, screenh - 309 - 140, null);
		g.drawImage(yes, 312, screenh - 165 - 133, null);
		g.drawImage(no, 584, screenh - 165 - 133, null);
		g.setFont(new Font(font.getName(), font.getStyle(), 144));
		g.drawString("Battering Valhalla", 39, screenh - 594);
		g.setFont(new Font(font.getName(), font.getStyle(), 96));
		g.drawString("Are you sure ?", 236, screenh - 315 - 96 / 3);
	}

	public int listener(int x, int y) {
		if (yes_circle.contains(x, y)) {
			return 1;
		} else if (no_circle.contains(x, y)) {
			return 2;
		}
		return 0;
	}
}
