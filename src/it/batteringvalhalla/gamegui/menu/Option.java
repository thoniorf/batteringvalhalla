package it.batteringvalhalla.gamegui.menu;

import java.awt.Color;
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

import javafx.scene.shape.Circle;

import javax.swing.JPanel;

public class Option extends JPanel {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	Image img;
	Image i;
	Image tmp;
	Image on;
	Image on2;
	Image ontmp;
	Image off;
	Image off2;
	Image offtmp;
	Font font;
	Circle off_circle;
	Circle on_circle;
	Image back;

	public Option() {
		setBackground(Color.PINK);

		MediaTracker mt = new MediaTracker(this);

		img = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass()
								.getResource(
										"../../assets/gui/icon/pink_button_sound_on_morgaine1976.png"))
				.getScaledInstance(900, 900, java.awt.Image.SCALE_SMOOTH);

		i = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass()
								.getResource(
										"../../assets/gui/icon/pink_button_sound_off_morgaine1976.png"))
				.getScaledInstance(900, 900, java.awt.Image.SCALE_SMOOTH);

		on = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/icon/bottonon.png"))
				.getScaledInstance(900, 900, java.awt.Image.SCALE_SMOOTH);

		on2 = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/icon/hover/senzanome.png"))
				.getScaledInstance(900, 900, java.awt.Image.SCALE_SMOOTH);

		off = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource("../../assets/gui/icon/off.png"))
				.getScaledInstance(900, 900, java.awt.Image.SCALE_SMOOTH);
		off2 = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/icon/hover/off2.png"))
				.getScaledInstance(900, 900, java.awt.Image.SCALE_SMOOTH);
		back = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass()
								.getResource(
										"../../assets/gui/menu/background/pngbackground.png"))
				.getScaledInstance(900, 900, java.awt.Image.SCALE_SMOOTH);

		mt.addImage(img, 0);
		mt.addImage(i, 1);
		mt.addImage(on, 2);
		mt.addImage(on2, 3);
		mt.addImage(off, 4);
		mt.addImage(off2, 5);
		mt.addImage(back, 6);

		tmp = img;
		ontmp = on;
		offtmp = off;
		try {
			mt.waitForAll();
			this.font = Font.createFont(Font.TRUETYPE_FONT, getClass()
					.getResourceAsStream("Deutsch.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (InterruptedException | FontFormatException | IOException e) {
			e.printStackTrace();
		}
		off_circle = new Circle();
		off_circle.setCenterX(735);
		off_circle.setCenterY(185);
		off_circle.setRadius(45);

		on_circle = new Circle();
		on_circle.setCenterX(600);
		on_circle.setCenterY(185);
		on_circle.setRadius(45);

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (on_circle.contains(e.getX(), e.getY())) {

					on = on2;
					repaint();

				} else {
					on = ontmp;
					repaint();
				}

				if (off_circle.contains(e.getX(), e.getY())) {
					off = off2;
					repaint();
				} else {
					off = offtmp;
					repaint();
				}

			}

		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (on_circle.contains(e.getX(), e.getY())) {
					on();

				}

				if (off_circle.contains(e.getX(), e.getY())) {
					onNo();

				}

			}
		});

	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(back, 200, 45, 600, 600, null);
		g.setFont(new Font(font.getName(), font.getStyle(), 60));
		g.drawString("Sound  ON / OFF", 210, 130);
		g.drawImage(img, 220, 135, 90, 90, null);
		g.drawImage(on, 550, 135, 90, 90, null);
		g.drawImage(off, 680, 135, 90, 90, null);
		g.setFont(new Font(font.getName(), font.getStyle(), 70));

		g.drawString("Tutorial", 220, 285);
		g.setFont(new Font(font.getName(), font.getStyle(), 67));
		g.drawString("Torna al menu'", 380, 580);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

	}

	void on() {
		img = tmp;
		repaint();
	}

	void onNo() {
		img = i;
		repaint();
	}

}
