package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamegui.menu.MainMenu;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2319946450620451636L;
	private Integer screen_width;
	private Integer screen_height;
	private Boolean fullscreen;
	private Dimension resolution;

	private MainMenu mainmenu;

	public Dimension getResolution() {
		return resolution;
	}

	public void setResolution(Dimension resolution, Boolean fullscreen) {
		this.resolution = resolution;
		this.fullscreen = fullscreen;
		this.setResizable(!fullscreen);
		this.setPreferredSize(resolution);
		this.setUndecorated(fullscreen);
		this.pack();
	}

	public Boolean getFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(Boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	private void init() {
		mainmenu = new MainMenu(this);
		this.setContentPane(mainmenu);
	}

	public GameFrame() throws HeadlessException {
		this.fullscreen = true;
		this.screen_width = 1024;
		this.screen_height = 768;

		this.setResolution(
				new Dimension(this.screen_width, this.screen_height), true);

		this.setTitle("Battering Valhalla");

		this.setLocationRelativeTo(null);

		this.init();
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new GameFrame();
	}

}
