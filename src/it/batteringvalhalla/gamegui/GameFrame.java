package it.batteringvalhalla.gamegui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamegui.editorActor.EditorPanel;
import it.batteringvalhalla.gamegui.menu.ExitMenu;
import it.batteringvalhalla.gamegui.menu.MainMenu;
import it.batteringvalhalla.gamegui.menu.OptionMenu;
import it.batteringvalhalla.gamegui.menu.ScoreBoard;
import it.batteringvalhalla.gamegui.menu.UsernameMenu;
import it.batteringvalhalla.gamegui.progress.Loading;
import it.batteringvalhalla.gamegui.sound.Sound;

/*	0			0			1			1			2
 * Loading -> MainMenu -> UserMenu -> OptionMenu -> Exit Menu
 */
public class GameFrame extends JFrame {

	private static GameFrame frame = null;

	private static final long serialVersionUID = 1L;

	public static GameFrame instance() {
		if (frame == null) {
			frame = new GameFrame();
		}
		return frame;
	}

	public static void main(String[] args) {
		GameFrame frame = GameFrame.instance();
		frame.showLoading();

		frame.start();
	}

	public void start() {
		this.getLayeredPane().removeAll();
		this.showMenu();
		this.getLayeredPane().getComponentsInLayer(0)[0].setEnabled(false);
		this.showUserField();
	}

	private Boolean fullscreen;
	private JPanel panel;
	private Dimension resolution;

	private Integer screen_height;
	private Integer screen_width;

	private GameFrame() {
		this.setTitle("Battering Valhalla");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.init();
		this.setVisible(true);
		new ManagerFilePlayer();
	}

	public Boolean getFullscreen() {
		return fullscreen;
	}

	public Dimension getResolution() {
		return resolution;
	}

	public Integer getScreen_height() {
		return screen_height;
	}

	public Integer getScreen_width() {
		return screen_width;
	}

	private void init() {
		this.fullscreen = true;
		Toolkit t = Toolkit.getDefaultToolkit();
		this.screen_width = (int) t.getScreenSize().getWidth();
		this.screen_height = (int) t.getScreenSize().getHeight();
		this.setResolution(t.getScreenSize(), true);

	}

	public void addMenu(JPanel panel, int index) {
		this.getLayeredPane().add(panel, new Integer(index));
		panel.updateUI();
		panel.requestFocus();
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void setFullscreen(Boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public void setResolution(Dimension resolution, Boolean fullscreen) {
		this.resolution = resolution;
		this.fullscreen = fullscreen;
		this.setResizable(!fullscreen);
		this.setPreferredSize(resolution);
		this.setUndecorated(fullscreen);
	}

	public void setScreen_height(Integer screen_height) {
		this.screen_height = screen_height;
	}

	public void setScreen_width(Integer screen_width) {
		this.screen_width = screen_width;
	}

	public void showEditor() {
		panel = new EditorPanel(this);
		this.setContentPane(panel);
	}

	public void showExit() {
		panel = new ExitMenu();
		addMenu(panel, 2);
	}

	public void showLoading() {
		panel = new Loading();
		addMenu(panel, 0);
		try {
			((Loading) panel).loadResources();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void showMenu() {
		panel = new MainMenu();
		addMenu(panel, 0);

	}

	public void showOptions() {
		panel = new OptionMenu();
		addMenu(panel, 1);
	}

	public void showScores() {
		panel = new ScoreBoard(this);
		addMenu(panel, 1);
	}

	public void showUserField() {
		panel = new UsernameMenu();
		addMenu(panel, 1);
		((UsernameMenu) panel).getUserfield().requestFocusInWindow();
		((UsernameMenu) panel).getUserfield().selectAll();

	}

	public void startGame() {
		panel = new GamePanel(this);
		this.getLayeredPane().removeAll();
		addMenu(panel, 0);
		((GamePanel) panel).getManager().run();
		if (ManagerFilePlayer.soundOn()) {
			Sound.menu.stop();
			Sound.battle.setRepeat(true);
			Sound.battle.play();
		} else {
			Sound.battle.stop();
		}

	}
}