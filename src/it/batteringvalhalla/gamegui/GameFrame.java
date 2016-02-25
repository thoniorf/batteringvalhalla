package it.batteringvalhalla.gamegui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.GameWorld;
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

	public static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	public static final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	private static final long serialVersionUID = 1L;
	private static GameFrame frame = null;
	private JPanel panel;
	private JLayeredPane layers;

	public static GameFrame instance() {
		if (frame == null) {
			frame = new GameFrame();
		}
		return frame;
	}

	private GameFrame() {
		this.setTitle("Battering Valhalla");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setPreferredSize(size);
		this.setUndecorated(true);
		this.setResizable(false);
		// device.setFullScreenWindow(this);
		this.setVisible(true);
		layers = getLayeredPane();
	}

	public void addMenu(JPanel panel, int index) {
		layers.add(panel, new Integer(index));
		panel.updateUI();
		panel.requestFocus();
		pack();
		setLocationRelativeTo(null);
	}

	public void restart() {
		if (ManagerFilePlayer.soundOn()) {
			Sound.battle.stop();
			Sound.menu.play();
		}
		layers.removeAll();
		showMenu();
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
		background.setBounds(0, 0, size.width, size.height);
		background.setVisible(true);
		addMenu(background, 0);
	}

	public void showEditor() {
		addMenu(new EditorPanel(), 2);
	}

	public void showExit() {
		addMenu(new ExitMenu(), 3);
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
		addMenu(new MainMenu(), 1);

	}

	public void showOptions() {
		addMenu(new OptionMenu(), 2);
	}

	public void showScores() {
		addMenu(new ScoreBoard(), 2);
	}

	public void start() {
		if (ManagerFilePlayer.soundOn()) {
			Sound.menu.play();
			Sound.menu.setRepeat(true);
		}

		// remove all layers
		layers.removeAll();
		// show main menu
		showMenu();
		// disable main menu
		layers.getComponentsInLayer(1)[0].setEnabled(false);
		// show username box
		panel = new UsernameMenu();
		addMenu(panel, 2);
		((UsernameMenu) panel).getUserfield().requestFocusInWindow();
		((UsernameMenu) panel).getUserfield().selectAll();
	}

	public void startGame() {
		// remove all layers
		layers.removeAll();
		// paint background
		setUiBackground();
		// create game panel and add
		panel = new GamePanel();
		addMenu(panel, 1);
		// set viewport for the manager
		GameManager.getManager();
		GameWorld.getWorld();
		GameManager.getManager().setViewport(panel);
		// start the game
		new Thread(GameManager.getManager()).start();
		// play the music
		if (ManagerFilePlayer.soundOn()) {
			Sound.menu.stop();
			Sound.battle.setRepeat(true);
			Sound.battle.play();
		} else {
			Sound.battle.stop();
		}

	}
}