package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamegui.GameFrame;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JPanel;

public class ExitMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1542197631298382722L;
	GameFrame gameframe;
	Image background;
	Image yes;
	Circle yes_circle;
	Image no;
	Circle no_circle;
	Font font;
	int screenh = 768;
	public boolean enabled = false;

	public ExitMenu(GameFrame gameFrame) {
		super();
		this.gameframe = gameFrame;
		this.mediaLoader();
	}

	private void mediaLoader() {
		MediaTracker mt = new MediaTracker(this);

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
			this.font = Font.createFont(Font.TRUETYPE_FONT, getClass()
					.getResourceAsStream("/assets/gui/Fonts/Deutsch.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (InterruptedException | FontFormatException | IOException e) {
			e.printStackTrace();
		}

		yes_circle = new Circle();
		yes_circle.setCenterX(312 + yes.getWidth(this) / 2);
		yes_circle.setCenterY((screenh - 165 - 133) + yes.getHeight(this) / 2);
		yes_circle.setRadius(yes.getHeight(this) / 2);

		no_circle = new Circle();
		no_circle.setCenterX(584 + no.getWidth(this) / 2);
		no_circle.setCenterY((screenh - 165 - 133) + no.getHeight(this) / 2);
		no_circle.setRadius(no.getHeight(this) / 2);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 182, screenh - 309 - 140, null);
		g.drawImage(yes, 312, screenh - 165 - 133, null);
		g.drawImage(no, 584, screenh - 165 - 133, null);
		g.setFont(new Font(font.getName(), font.getStyle(), 144));
		g.drawString("Battering Valhalla", 39, screenh - 594);
		g.setFont(new Font(font.getName(), font.getStyle(), 96));
		g.drawString("Are you sure ?", 236, screenh - 315 - 96 / 3);
	}

	private void listenerLoader() {
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if (enabled)
					switch (listener(e.getX(), e.getY())) {
					case 1:
						System.exit(0);
						break;
					case 2:
						enabled = false;
						break;
					}
				// repaint();
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
}
