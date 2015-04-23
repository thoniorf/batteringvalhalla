package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamecore.GameManager;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private Integer screen_width;
	private Integer screen_height;
	private Boolean fullscreen;
	private Dimension resolution;

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
		new GameFrame().start();
	}

	private void start() {
		GameManager manager = new GameManager(this);
		manager.init();
		manager.run();
	}

}
