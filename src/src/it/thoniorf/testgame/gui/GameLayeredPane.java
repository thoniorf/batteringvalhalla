package it.thoniorf.testgame.gui;

import it.thoniorf.testgame.GameManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class GameLayeredPane extends JLayeredPane {

	MainMenu main;
	ExitMenu exit;
	Font gothic;
	GameManager game;
	JFrame frame;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(1024, 768));
		// frame.setMinimumSize(new Dimension(1024, 768));
		frame.setUndecorated(true);

		frame.setBackground(Color.CYAN);

		frame.add(new GameLayeredPane(frame));

		frame.pack();
		frame.setLocationRelativeTo(null);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	public GameLayeredPane(JFrame frame) {
		super();
		this.frame = frame;
		try {
			gothic = Font.createFont(Font.TRUETYPE_FONT, getClass()
					.getResourceAsStream("/assets/gui/Fonts/Deutsch.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			ge.registerFont(gothic);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		main = new MainMenu(gothic, this);
		main.enabled = true;
		exit = new ExitMenu(gothic, this);

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if (main.enabled)
					switch (main.listener(e.getX(), e.getY())) {
					case 1:
						game = new GameManager();
						frame.setFocusable(false);
						frame.setVisible(false);
						game.start();
						break;
					case 2:
						System.out.println("options");
						break;
					case 3:
						exit.enabled = true;
						main.enabled = false;
						break;
					}
				if (exit.enabled)
					switch (exit.listener(e.getX(), e.getY())) {
					case 1:
						System.exit(0);
						break;
					case 2:
						exit.enabled = false;
						main.enabled = true;
						break;
					}
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if (main.enabled)
			main.paint(g);
		else if (exit.enabled)
			exit.paint(g);
	}
}
