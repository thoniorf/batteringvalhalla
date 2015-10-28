package it.batteringvalhalla.gamegui;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.batteringvalhalla.gamegui.menu.ExitMenu;
import it.batteringvalhalla.gamegui.menu.MainMenu;
import it.batteringvalhalla.gamegui.menu.OptionMenu;
import it.batteringvalhalla.gamegui.menu.ScoreBoard;
import it.batteringvalhalla.gamegui.menu.UsernameMenu;
import it.batteringvalhalla.gamegui.progress.LoadProgress;
import it.batteringvalhalla.gamegui.sound.FileSound;
import it.batteringvalhalla.gamegui.sound.Sound;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = -5257465474614819083L;

	public static void main(String[] args) {
		GameFrame frame = GameFrame.instance();
		frame.loading();
		frame.start();
	}

	FileSound f;
	private Boolean fullscreen;
	private JPanel panel;
	private Dimension resolution;

	private Integer screen_height;
	private Integer screen_width;

	private static GameFrame frame = null;

	private GameFrame() throws HeadlessException {
		this.fullscreen = true;
		this.screen_width = 1024;
		this.screen_height = 768;
		this.setResolution(new Dimension(screen_width, screen_height), fullscreen);
		this.setTitle("Battering Valhalla");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public static GameFrame instance() {
		if (frame == null)
			return new GameFrame();
		return frame;
	}

	public void backMenu() {
		setPanel(new MainMenu(this));

	}

	public void exit() throws InterruptedException {
		setPanel(new ExitMenu(this));
	}

	public void gameStart() throws InterruptedException {
		setPanel(new GamePanel(this));
		((GamePanel) panel).getManager().run();
		f = new FileSound();
		if ((f.read()).equals("0")) {
			Sound.menu.stop();
			Sound.battle.setRepeat(true);
			Sound.battle.play();
		} else {
			Sound.battle.stop();
		}

	}

	public FileSound getF() {
		return f;
	}

	public Boolean getFullscreen() {
		return fullscreen;
	}

	public Dimension getResolution() {
		return resolution;
	}

	public void loading() {
		setPanel(new LoadProgress(this));
		((LoadProgress) panel).run();
	}

	public void menuStart() {
		setPanel(new MainMenu(this));
	}

	public void opt() throws InterruptedException {
		setPanel(new OptionMenu(this));
	}

	public void setFullscreen(Boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
		this.setContentPane(panel);
		panel.requestFocus();
		panel.updateUI();
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void setResolution(Dimension resolution, Boolean fullscreen) {
		this.resolution = resolution;
		this.fullscreen = fullscreen;
		this.setResizable(!fullscreen);
		this.setPreferredSize(resolution);
		this.setUndecorated(fullscreen);
	}

	public void showScores() throws InterruptedException {
		setPanel(new ScoreBoard(this));
	}

	private void start() {
		menuStart();
		f = new FileSound();
		if ((f.read()).equals("0")) {
			Sound.menu.setRepeat(true);
			Sound.menu.play();
		} else {
			Sound.menu.stop();
		}
		userField();

	}

	public void userField() {
		setPanel(new UsernameMenu(this));
		((UsernameMenu) panel).getUserfield().requestFocusInWindow();
		((UsernameMenu) panel).getUserfield().selectAll();

	}
}