package it.thoniorf.testgame.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class GameLayeredPane extends JLayeredPane {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBackground(Color.CYAN);

		frame.add(new GameLayeredPane());

		frame.pack();
		frame.setVisible(true);
	}

	public GameLayeredPane() {
		super();
		JLabel mainmenu = new JLabel("LALA");
		mainmenu.setSize(new Dimension(100, 200));
		mainmenu.setPreferredSize(new Dimension(100, 200));
		mainmenu.setBackground(new Color(255, 0, 0, 64));
		mainmenu.setVisible(true);
		this.add(mainmenu, 0);
		JLabel optionmenu = new JLabel("Lavala");
		optionmenu.setSize(new Dimension(100, 200));
		optionmenu.setPreferredSize(new Dimension(100, 200));
		optionmenu.setBackground(new Color(0, 255, 0, 0));
		optionmenu.setVisible(true);
		this.add(optionmenu, 1);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
}
