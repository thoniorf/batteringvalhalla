package it.batteringvalhalla.gamegui;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLayeredPane;

import it.batteringvalhalla.gamegui.menu.MainMenu;

public class LayeredPanel extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	public static LayeredPanel panel = null;

	private LayeredPanel() {
		Point origin = new Point((GameFrame.instance().getScreen_width() / 2) - (1024 / 2),
				(GameFrame.instance().getScreen_height() / 2) - (768 / 2));
		this.setPreferredSize(new Dimension(1024, 768));
		this.setLocation(origin);
		this.setVisible(true);

		init();
	}

	public static LayeredPanel instance() {
		if (panel == null)
			panel = new LayeredPanel();
		return panel;
	}

	private void init() {
		add(new MainMenu(), -1);

	}

}
