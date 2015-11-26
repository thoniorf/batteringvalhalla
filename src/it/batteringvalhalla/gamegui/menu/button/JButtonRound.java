package it.batteringvalhalla.gamegui.menu.button;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonRound extends JButton {
	private static final long serialVersionUID = 1L;
	private Circle2D circle;
	private Integer width;
	private Integer height;
	private ImageIcon image;
	private ImageIcon imageHover;

	public JButtonRound(Image image, Image imageHover) {
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		this.image = new ImageIcon(image);
		this.imageHover = new ImageIcon(imageHover);
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setIcon(this.image);
		setRolloverIcon(this.imageHover);
		setPressedIcon(this.imageHover);
		setDisabledIcon(this.image);
		this.circle = new Circle2D(width / 2, height / 2, image.getWidth(null) / 2);
	}

	@Override
	public boolean contains(int x, int y) {
		return circle.contains(x, y);
	}
}
