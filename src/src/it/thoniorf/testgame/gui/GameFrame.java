package it.thoniorf.testgame.gui;

import it.thoniorf.testgame.GameWorld;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	GamePanel gp;

	public GameFrame(GameWorld world) {
		super("TestGame");
		this.setVisible(true);

		gp = new GamePanel(world);
		this.add(gp);
		this.setContentPane(gp);
		this.setSize(605, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void update() {
		gp.updateGUI();
		gp.repaint();
	}

	public void popUp() {
		JLabel label = new JLabel("Game Over !");
		this.add(label);
		label.setBounds(250, 250, 200, 200);
		label.setVisible(true);

	}
}
