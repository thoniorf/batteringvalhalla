package it.batteringvalhalla.gamegui.menu.button;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class JButtonRound extends JButton {
	private Image image;
	private Image imageHover;
	private Image draw;
	private Circle2D circle;
	private Integer width;
	private Integer height;

	public JButtonRound(Image image, Image imageHover) {
		this.image = image;
		this.imageHover = imageHover;
		this.draw = image;
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setContentAreaFilled(false);
		setFocusPainted(false);
		this.circle = new Circle2D(width / 2, height / 2, image.getWidth(null) / 2);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				draw = imageHover;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				draw = image;
				repaint();
			}
		});

	}

	@Override
	public boolean contains(int x, int y) {
		return circle.contains(x, y);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawImage(draw, 0, 0, null);
	}

}
