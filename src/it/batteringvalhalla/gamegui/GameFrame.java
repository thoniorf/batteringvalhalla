package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.menu.ExitMenu;
import it.batteringvalhalla.gamegui.menu.MainMenu;
import it.batteringvalhalla.gamegui.menu.OptionMenu;
import it.batteringvalhalla.gamegui.menu.ScoreBoard;
import it.batteringvalhalla.gamegui.menu.UsernameMenu;
import it.batteringvalhalla.gamegui.sound.FileSound;
import it.batteringvalhalla.gamegui.sound.Sound;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5257465474614819083L;
	private Integer screen_width;
	private Integer screen_height;
	private Boolean fullscreen;
	private Dimension resolution;

	private JPanel panel;
	FileSound f;

	public FileSound getF() {
		return f;
	}

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
		try {
			ResourcesLoader.loadPlayerImages();
			ResourcesLoader.loadMainMenuImages();
			ResourcesLoader.loadExitMenuImages();
			ResourcesLoader.loadScoreBoardImages();
			ResourcesLoader.loadOptionMenuImages();
			ResourcesLoader.loadFont();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Sound.loadSound();
		GameFrame frame = new GameFrame();
		frame.start();

	}

	private void start() {
		menuStart();
		f = new FileSound();
		if ((f.read()).equals("0")) {
			Sound.ok.setRepeat(true);
			Sound.ok.play();
		} else {
			Sound.ok.stop();
		}
		userField();

	}

	public void menuStart() {
		panel = new MainMenu(this);
		this.setContentPane(panel);
		panel.requestFocus();
		panel.updateUI();
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void gameStart() throws InterruptedException {
		panel = new GamePanel(this);
		this.setContentPane(panel);
		panel.updateUI();
		panel.requestFocus();
		this.pack();
		this.setLocationRelativeTo(null);
		((GamePanel) panel).getManager().run();

	}

	public void showScores() throws InterruptedException {
		panel = new ScoreBoard(this);
		this.setContentPane(panel);
		panel.updateUI();
		panel.requestFocus();
		this.pack();
		this.setLocationRelativeTo(null);

	}

	public void exit() throws InterruptedException {
		panel = new ExitMenu(this);
		this.setContentPane(panel);
		panel.updateUI();
		panel.requestFocus();
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void opt() throws InterruptedException {
		panel = new OptionMenu(this);
		this.setContentPane(panel);
		panel.updateUI();
		panel.requestFocus();
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void backMenu() {
		panel = new MainMenu(this);
		this.setContentPane(panel);
		panel.updateUI();
		panel.requestFocus();
		this.pack();
		this.setLocationRelativeTo(null);

	}

	public void userField() {
		panel = new UsernameMenu(this);
		this.setContentPane(panel);
		panel.updateUI();
		panel.requestFocus();
		this.pack();
		this.setLocationRelativeTo(null);
		((UsernameMenu) panel).getUserfield().requestFocusInWindow();
		((UsernameMenu) panel).getUserfield().selectAll();

	}
}
