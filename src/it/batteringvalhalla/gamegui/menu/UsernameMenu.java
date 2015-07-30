package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.GameFrame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javafx.scene.shape.Circle;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class UsernameMenu extends JPanel {

	private static final long serialVersionUID = -1514224030058165182L;
	GameFrame frame;
	JTextField userfield;
	Circle yes_circle;
	Circle no_circle;
	int screenh = 768;

	public UsernameMenu(GameFrame frame) {
		super(null);
		this.frame = frame;
		userfield = new JTextField();
		this.add(userfield);
		userfield.setBounds(182, screenh - 309 - 140, 250, 70);
		this.mediaLoader();
		this.listenerLoader();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(ResourcesLoader.exitmenu_images.get(0), 182,
				screenh - 309 - 140, null);
		g.drawImage(ResourcesLoader.exitmenu_images.get(1), 312,
				screenh - 165 - 133, null);
		g.drawImage(ResourcesLoader.exitmenu_images.get(2), 584,
				screenh - 165 - 133, null);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 96));
		g.drawString("Are you sure ?", 236, screenh - 315 - 96 / 3);
	}

	private void mediaLoader() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		ge.registerFont(ResourcesLoader.gothic);

		yes_circle = new Circle();
		yes_circle.setCenterX(312 + ResourcesLoader.exitmenu_images.get(1)
				.getWidth(this) / 2);
		yes_circle.setCenterY((screenh - 165 - 133)
				+ ResourcesLoader.exitmenu_images.get(1).getHeight(this) / 2);
		yes_circle.setRadius(ResourcesLoader.exitmenu_images.get(1).getHeight(
				this) / 2);

		no_circle = new Circle();
		no_circle.setCenterX(584 + ResourcesLoader.exitmenu_images.get(2)
				.getWidth(this) / 2);
		no_circle.setCenterY((screenh - 165 - 133)
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
					// System.exit(0);
					break;
				case 2:
					// gameframe.menuStart();
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
