package it.batteringvalhalla.gamegui.menu.button;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonCustom extends JButton {
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private ImageIcon image, imageHover, selectedHover;

	public JButtonCustom(Image image, Image imageHover, Image selectedHover) {
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		this.image = new ImageIcon(image);
		this.imageHover = new ImageIcon(imageHover);
		this.selectedHover = new ImageIcon(selectedHover);
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setIcon(this.image);
		setRolloverIcon(this.imageHover);
		setPressedIcon(this.selectedHover);
		setDisabledIcon(new ImageIcon(image));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				setLocation(getBounds().x, getBounds().y + 10);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				setLocation(getBounds().x, getBounds().y - 10);
			}
		});
	}
}
