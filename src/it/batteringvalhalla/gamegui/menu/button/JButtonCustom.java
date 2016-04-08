package it.batteringvalhalla.gamegui.menu.button;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamegui.sound.Sound;

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
	private ImageIcon image, imageHover, imageSelected;

	public JButtonCustom(Image image, Image imageHover, Image imageSelected) {
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		this.image = new ImageIcon(image);
		this.imageHover = new ImageIcon(imageHover);
		this.imageSelected = new ImageIcon(imageSelected);
		setPreferredSize(new Dimension(width, height));
		setSize(width, height);
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setIcon(this.image);
		setRolloverIcon(this.imageHover);
		setPressedIcon(this.imageSelected);
		setDisabledIcon(this.image);
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
				if (ManagerFilePlayer.soundOn()) {
					Sound.button().setFramePosition(0);
					Sound.button().start();
				}
			}
		});
	}
}
