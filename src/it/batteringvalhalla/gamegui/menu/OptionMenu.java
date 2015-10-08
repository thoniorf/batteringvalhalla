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

	Image on;
	Circle on_circle;
	Image off;
	Circle off_circle;
	GameFrame frame;
	Image back;
	Circle back_circle;
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

			on = ResourcesLoader.optionmenu_images.get(1);
			off = ResourcesLoader.optionmenu_images.get(2);

		} else {

			off = ResourcesLoader.optionmenu_images.get(3);
			on = ResourcesLoader.optionmenu_images.get(0);
		}

		back = ResourcesLoader.optionmenu_images.get(5);
		off_circle = new Circle();
		off_circle.setCenterX(745);
		off_circle.setCenterY(195);
		off_circle.setRadius(45);

		on_circle = new Circle();
		on_circle.setCenterX(610);
		on_circle.setCenterY(195);
		on_circle.setRadius(45);

		back_circle = new Circle();
		back_circle.setCenterX(295);
		back_circle.setCenterY(550);
		back_circle.setRadius(35);

	}

	private void listener() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				if (back_circle.contains(e.getX(), e.getY())) {
					back = ResourcesLoader.optionmenu_images.get(6);

				} else {
					back = ResourcesLoader.optionmenu_images.get(5);

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
					Sound.menu.play();

				}

				if (off_circle.contains(e.getX(), e.getY())) {
					off();
					f.write1();
					Sound.menu.stop();

				}

				if (back_circle.contains(e.getX(), e.getY())) {
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
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.drawImage(ResourcesLoader.optionmenu_images.get(4), 200, 45, null);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 60));
		g.drawString("Sound  ON / OFF", 210, 130);
		g.drawImage(on, 555, 145, null);
		g.drawImage(off, 685, 145, null);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 67));
		g.drawImage(back, 230, 500, null);
		g.drawImage(ResourcesLoader.optionmenu_images.get(7), 320, 245, null);
		g.drawImage(ResourcesLoader.optionmenu_images.get(8), 320, 300, null);
		g.drawImage(ResourcesLoader.optionmenu_images.get(9), 265, 270, null);
		g.drawImage(ResourcesLoader.optionmenu_images.get(10), 375, 270, null);
		g.drawString("Move", 265, 408);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 64));

	}

	void on() {
		on = ResourcesLoader.optionmenu_images.get(1);
		off = ResourcesLoader.optionmenu_images.get(2);
		repaint();

	}

	void off() {
		off = ResourcesLoader.optionmenu_images.get(3);
		on = ResourcesLoader.optionmenu_images.get(0);
		repaint();

	}

}
