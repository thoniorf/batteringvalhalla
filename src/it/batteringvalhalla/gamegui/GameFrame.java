package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamegui.menu.MainMenu;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer screen_width;
	private Integer screen_height;
	private Boolean fullscreen;
	private Dimension resolution;

	MainMenu main;

	public Dimension getResolution() {
		return resolution;
	}

	public void setResolution(Dimension resolution, Boolean fullscreen) {
		this.resolution = resolution;
		this.fullscreen = fullscreen;
		this.setResizable(!fullscreen);
		this.setPreferredSize(resolution);
		this.setUndecorated(fullscreen);
	}

	public Boolean getFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(Boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	private void init() {
		this.fullscreen = true;
		this.screen_width = 1024;
		this.screen_height = 768;
		this.setResolution(
				new Dimension(this.screen_width, this.screen_height), true);
	}

	public GameFrame() throws HeadlessException {
		this.init();
		this.setTitle("Battering Valhalla");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new GameFrame().start();
	}

	private void start() {
		gameStart();
		/*
		 * main = new MainMenu(this); this.setContentPane(main); this.pack();
		 * this.setLocationRelativeTo(null);
		 */
	}

	public void gameStart() {
		GameManager manager = new GameManager();
		manager.init(this);
		this.setContentPane(manager.getGamePanel());
		manager.getGamePanel().updateUI();
		manager.getGamePanel().requestFocus();
		System.out.println(manager.getGamePanel().isFocusable());
		// SwingUtilities.invokeLater(manager);
		this.pack();
		this.setLocationRelativeTo(null);

		manager.run();
	}
}
