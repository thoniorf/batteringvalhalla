package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.sound.FileSound;
import it.batteringvalhalla.gamegui.sound.Sound;

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
	private static final long serialVersionUID = -1370627725870961582L;
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
	GameFrame frame;
	Image viki;
	Image v2;
	Image vtmp;
	Circle viki_circle;
	FileSound f;

	public Option(GameFrame frame) {
		super();
		this.frame = frame;
		f = frame.getF();

		try {
			load();
		} catch (IOException e) {

			e.printStackTrace();
		}
		listener();

	}

	private void load() throws IOException {
		MediaTracker mt = new MediaTracker(this);

		img = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass()
								.getResource(
										"../../assets/gui/menu/icon/pink_button_sound_on_morgaine1976.png"));

		i = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass()
								.getResource(
										"../../assets/gui/menu/icon/pink_button_sound_off_morgaine1976.png"));

		on = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(
						"../../assets/gui/menu/icon/bottonon.png"));
		on2 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(
						"../../assets/gui/menu/icon/hover/Senzanome.png"));

		off = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("../../assets/gui/menu/icon/off.png"));

		off2 = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(
						"../../assets/gui/menu/icon/hover/off2.png"));
		back = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass()
								.getResource(
										"../../assets/gui/menu/background/exit_background.png"));

		viki = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/menu/icon/di4875BKT.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		v2 = Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"../../assets/gui/menu/icon/hover/viki.png"))
				.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);

		mt.addImage(img, 1);
		mt.addImage(i, 2);
		mt.addImage(on, 3);
		mt.addImage(on2, 4);
		mt.addImage(back, 0);
		mt.addImage(off, 5);
		mt.addImage(off2, 6);
		mt.addImage(viki, 7);
		mt.addImage(v2, 8);
		tmp = img;
		ontmp = on;
		offtmp = off;
		vtmp = viki;

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

		off_circle = new Circle();
		off_circle.setCenterX(735);
		off_circle.setCenterY(185);
		off_circle.setRadius(45);

		on_circle = new Circle();
		on_circle.setCenterX(600);
		on_circle.setCenterY(185);
		on_circle.setRadius(45);

		viki_circle = new Circle();
		viki_circle.setCenterX(305);
		viki_circle.setCenterY(550);
		viki_circle.setRadius(35);

	}

	private void listener() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (on_circle.contains(e.getX(), e.getY())) {

					on = on2;

				} else {
					on = ontmp;

				}

				if (off_circle.contains(e.getX(), e.getY())) {
					off = off2;

				} else {
					off = offtmp;

				}

				if (viki_circle.contains(e.getX(), e.getY())) {
					viki = v2;

				} else {
					viki = vtmp;

				}

				repaint();
			}

		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (on_circle.contains(e.getX(), e.getY())) {
					on();

					f.write0();
					if (f.read().equals("0")) {
						Sound.ok.play();

					} else {
						Sound.ok.stop();
					}
				}

				if (off_circle.contains(e.getX(), e.getY())) {
					onNo();
					f.write1();
					if (f.read().equals("0")) {
						Sound.ok.play();

					} else {

						Sound.ok.stop();
					}
				}

				if (viki_circle.contains(e.getX(), e.getY())) {
					frame.backMenu();
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
		g.drawImage(viki, 230, 500, 120, 100, null);

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
