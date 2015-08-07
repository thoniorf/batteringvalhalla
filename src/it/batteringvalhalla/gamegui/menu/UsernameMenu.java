package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.actor.Player;
import it.batteringvalhalla.gamegui.GameFrame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javafx.scene.shape.Circle;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

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
		this.mediaLoader();
		this.listenerLoader();
		userfield = new JTextField();
		this.add(userfield);
		userfield.setBorder(null);
		userfield.setOpaque(false);
		userfield.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 96));
		userfield.setHorizontalAlignment(JTextField.CENTER);
		userfield.setBounds(198, screenh - 315 - 108,
				ResourcesLoader.exitmenu_images.get(0).getWidth(this) - 42, 96);
		userfield.setDocument(new PlainDocument() {
			private static final long serialVersionUID = 3970826291478127404L;

			@Override
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				if (str == null) {
					return;
				}

				if ((getLength() + str.length()) <= 8) {
					super.insertString(offs, str, a);
				}

			}
		});
		userfield.setText("Player 1");

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(ResourcesLoader.exitmenu_images.get(0), 182,
				screenh - 309 - 140, null);
		g.drawImage(ResourcesLoader.exitmenu_images.get(1), 312,
				screenh - 165 - 133, null);
		g.drawImage(ResourcesLoader.exitmenu_images.get(2), 584,
				screenh - 165 - 133, null);
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
					if (userfield.getText().length() != 0)
						Player.username = userfield.getText();
					else
						userfield.setText("Player 1");
					frame.menuStart();
					break;
				case 2:
					Player.username = "Player 1";
					frame.menuStart();
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

	public JTextField getUserfield() {
		return userfield;
	}
}