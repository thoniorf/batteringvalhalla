package it.batteringvalhalla.gamegui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.editorActor.EditorPanel;
import it.batteringvalhalla.gamegui.menu.ExitMenu;
import it.batteringvalhalla.gamegui.menu.MainMenu;
import it.batteringvalhalla.gamegui.menu.OptionMenu;
import it.batteringvalhalla.gamegui.menu.ScoreBoard;
import it.batteringvalhalla.gamegui.menu.UsernameMenu;
import it.batteringvalhalla.gamegui.progress.Loading;
import it.batteringvalhalla.gamegui.sound.Sound;

/*	0			0			1			2			2			  2				3
 * Loading -> Background -> MainMenu -> UserMenu -> OptionMenu -> EditorMenu -> Exit Menu
 * 1			2			 3
 * GamePanel -> PauseMenu -> ExitMenu
 */
public class GameFrame extends JFrame {

	private static GameFrame frame = null;
	private static final long serialVersionUID = 1L;

	private Boolean fullscreen;
	private JPanel panel;
	private Dimension resolution;
	private Integer screen_height;
	private Integer screen_width;

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

	private GameFrame() {
		this.setTitle("Battering Valhalla");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.init();
		this.setVisible(true);
		new ManagerFilePlayer();
	}

	public void addMenu(JPanel panel, int index) {
		this.getLayeredPane().add(panel, new Integer(index));
		panel.updateUI();
		panel.requestFocus();
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public Boolean getFullscreen() {
		return fullscreen;
	}

	public Dimension getResolution() {
		return resolution;
	}

	public Integer getScreenHeight() {
		return screen_height;
	}

	public Integer getScreenWidth() {
		return screen_width;
	}

	private void init() {
		this.fullscreen = true;
		Toolkit t = Toolkit.getDefaultToolkit();
		this.screen_width = (int) t.getScreenSize().getWidth();
		this.screen_height = (int) t.getScreenSize().getHeight();
		this.setResolution(t.getScreenSize(), true);

	}

	public void restart() {
		if (ManagerFilePlayer.soundOn()) {
			Sound.battle.stop();
			Sound.menu.play();
		}
		this.getLayeredPane().removeAll();
		this.showMenu();
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

	public void setScreenHeight(Integer screen_height) {
		this.screen_height = screen_height;
	}

	public void setScreenWidth(Integer screen_width) {
		this.screen_width = screen_width;
	}

	private void setUiBackground() {
		JPanel background = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(ResourcesLoader.mainmenu_images.get(10), 0, 0, null);
			}
		};
		background.setBounds(0, 0, getScreenWidth(), getScreenHeight());
		background.setVisible(true);
		addMenu(background, 0);
	}

	public void showEditor() {
		panel = new EditorPanel();
		addMenu(panel, 2);
	}

	public void showExit() {
		panel = new ExitMenu();
		addMenu(panel, 3);
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
		setUiBackground();
		panel = new MainMenu();
		addMenu(panel, 1);

	}

	public void showOptions() {
		panel = new OptionMenu();
		addMenu(panel, 2);
	}

	public void showScores() {
		panel = new ScoreBoard();
		addMenu(panel, 2);
	}

	public void showUserField() {
		panel = new UsernameMenu();
		addMenu(panel, 2);
		((UsernameMenu) panel).getUserfield().requestFocusInWindow();
		((UsernameMenu) panel).getUserfield().selectAll();

	}

	public void start() {
		if (ManagerFilePlayer.soundOn()) {
			Sound.menu.play();
			Sound.menu.setRepeat(true);
		}

		this.getLayeredPane().removeAll();
		this.showMenu();
		this.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(false);
		this.showUserField();
	}

	public void startGame() {
		this.getLayeredPane().removeAll();
		setUiBackground();
		panel = new GamePanel();
		addMenu(panel, 1);
		GameManager.startNewManager((GamePanel) panel);
		if (ManagerFilePlayer.soundOn()) {
			Sound.menu.stop();
			Sound.battle.setRepeat(true);
			Sound.battle.play();
		} else {
			Sound.battle.stop();
		}

	}
}