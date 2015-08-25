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

public class OptionMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1370627725870961582L;
	Image img;

	Image on;

	Image off;

	Circle off_circle;
	Circle on_circle;
	GameFrame frame;
	Image viki;
	Circle viki_circle;
	FileSound f;

	public OptionMenu(GameFrame frame) {
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

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		ge.registerFont(ResourcesLoader.gothic);
		if (f.read().equals("0")) {

			img = ResourcesLoader.optionmenu_images.get(0);

		} else {
			img = ResourcesLoader.optionmenu_images.get(1);
		}

		on = ResourcesLoader.optionmenu_images.get(2);
		off = ResourcesLoader.optionmenu_images.get(4);
		viki = ResourcesLoader.optionmenu_images.get(7);
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

					on = ResourcesLoader.optionmenu_images.get(3);

				} else {
					on = ResourcesLoader.optionmenu_images.get(2);

				}

				if (off_circle.contains(e.getX(), e.getY())) {
					off = ResourcesLoader.optionmenu_images.get(5);

				} else {
					off = ResourcesLoader.optionmenu_images.get(4);

				}

				if (viki_circle.contains(e.getX(), e.getY())) {
					viki = ResourcesLoader.optionmenu_images.get(8);

				} else {
					viki = ResourcesLoader.optionmenu_images.get(7);

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
					Sound.ok.play();

				}

				if (off_circle.contains(e.getX(), e.getY())) {
					onNo();
					f.write1();
					Sound.ok.stop();

				}

				if (viki_circle.contains(e.getX(), e.getY())) {
					if ((f.read()).equals("0")) {
						Sound.button.play();

					}
					frame.backMenu();
				}

			}
		});
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(ResourcesLoader.optionmenu_images.get(6), 200, 45, 600,
				600, null);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 60));
		g.drawString("Sound  ON / OFF", 210, 130);
		g.drawImage(img, 220, 135, 90, 90, null);
		g.drawImage(on, 550, 135, 90, 90, null);
		g.drawImage(off, 680, 135, 90, 90, null);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 67));
		g.drawString("Torna al menu'", 380, 580);
		g.drawImage(viki, 230, 500, 120, 100, null);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

	}

	void on() {
		img = ResourcesLoader.optionmenu_images.get(0);
		repaint();

	}

	void onNo() {
		img = ResourcesLoader.optionmenu_images.get(1);
		repaint();

	}

}
