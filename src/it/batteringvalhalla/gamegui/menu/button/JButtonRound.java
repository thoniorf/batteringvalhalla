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

public class JButtonRound extends JButton {
	private static final long serialVersionUID = 1L;
	private Circle2D circle;

	public JButtonRound(Image img, Image imghover) {
		Integer width = img.getWidth(null);
		Integer height = img.getHeight(null);
		ImageIcon image = new ImageIcon(img);
		ImageIcon imageHover = new ImageIcon(imghover);
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setIcon(image);
		setRolloverIcon(imageHover);
		setPressedIcon(imageHover);
		setSelectedIcon(imageHover);
		setDisabledIcon(image);
		this.circle = new Circle2D(width / 2, height / 2, img.getWidth(null) / 2);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if (ManagerFilePlayer.soundOn()) {
					Sound.button().setFramePosition(0);
					Sound.button().start();
				}
			}
		});
	}

	@Override
	public boolean contains(int x, int y) {
		return circle.contains(x, y);
	}
}
