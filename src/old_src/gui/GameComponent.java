package it.thoniorf.testgame.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GameComponent extends JComponent {
	private Image img;

	public GameComponent(Toolkit tk, String img, Integer x, Integer y) {
		this.setVisible(true);
		this.img = tk.getImage(getClass().getResource("/assets/" + img));
	}

	public void setCords(int x, int y) {
		// TODO Auto-generated method stub
		super.setBounds(x, y, img.getWidth(null), img.getHeight(null));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

}
