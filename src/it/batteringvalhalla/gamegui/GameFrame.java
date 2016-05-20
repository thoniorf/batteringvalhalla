package it.batteringvalhalla.gamegui;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.State;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.network.Client;
import it.batteringvalhalla.gamegui.editorActor.EditorPanel;
import it.batteringvalhalla.gamegui.editorMap.EditorMapPanel;
import it.batteringvalhalla.gamegui.menu.ArcadeMenu;
import it.batteringvalhalla.gamegui.menu.EditorsMenu;
import it.batteringvalhalla.gamegui.menu.ExitMenu;
import it.batteringvalhalla.gamegui.menu.HostMenu;
import it.batteringvalhalla.gamegui.menu.JoinMenu;
import it.batteringvalhalla.gamegui.menu.MainMenu;
import it.batteringvalhalla.gamegui.menu.OnlineErrorMenu;
import it.batteringvalhalla.gamegui.menu.OnlineMenu;
import it.batteringvalhalla.gamegui.menu.OptionMenu;
import it.batteringvalhalla.gamegui.menu.PauseMenu;
import it.batteringvalhalla.gamegui.menu.ScoreBoard;
import it.batteringvalhalla.gamegui.menu.UsernameMenu;
import it.batteringvalhalla.gamegui.menu.WaitMenu;
import it.batteringvalhalla.gamegui.progress.Loading;
import it.batteringvalhalla.gamegui.sound.Sound;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/*	0			0			1			2			2			  2				3
 * Loading -> Background -> MainMenu -> UserMenu -> OptionMenu -> EditorMenu -> Exit Menu
 * 1			2			 3
 * GamePanel -> PauseMenu -> ExitMenu
 */
public class GameFrame extends JFrame {

	public static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();// new
																						// Dimension(1024,768);//
	public static final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	private static final long serialVersionUID = 1L;
	private static GameFrame frame = null;
	private JPanel panel;
	private final JLayeredPane layers;

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
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		device.setFullScreenWindow(this);
		this.setVisible(true);
		this.layers = this.getLayeredPane();
	}

	public JPanel addMenu(JPanel panel, int index) {
		this.layers.add(panel, new Integer(index));
		panel.updateUI();
		panel.requestFocusInWindow();
		this.pack();
		return panel;
	}

	public void restart() {
		if (ManagerFilePlayer.soundOn() && (Sound.menu.isStopped())) {
			Sound.battle.stop();
			Sound.menu.play();
		}
		this.layers.removeAll();
		GameManager.setState(State.Stop);
		this.showMenu();
	}

	private void setUiBackground() {
		final JPanel background = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(ResourcesLoader.mainmenu_images.get(10), 0, 0, null);
			}
		};
		background.setBounds(0, 0, size.width, size.height);
		background.setVisible(true);
		this.addMenu(background, 0);
	}

	public void showArcadeEditor() {
		this.addMenu(new ArcadeMenu(), 2);
	}

	public void showEditor() {
		this.layers.remove(this.layers.getComponentsInLayer(2)[0]);
		this.addMenu(new EditorPanel(), 2);
	}

	public void showEditorsMenu() {
		this.addMenu(new EditorsMenu(), 2);
	}

	public void showEditorMap() {
		this.layers.remove(this.layers.getComponentsInLayer(2)[0]);
		this.addMenu(new EditorMapPanel(), 2);
	}

	public void showExit() {
		this.addMenu(new ExitMenu(), 3);
	}

	public void showLoading() {
		this.panel = new Loading();
		this.addMenu(this.panel, 0);
		this.setLocationRelativeTo(null);
		try {
			((Loading) this.panel).loadResources();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void showMenu() {
		this.setUiBackground();
		this.addMenu(new MainMenu(), 1);

	}

	public void showOptions() {
		this.addMenu(new OptionMenu(), 2);
	}

	public void showScores() {
		this.addMenu(new ScoreBoard(), 2);
	}

	public void start() {
		if (ManagerFilePlayer.soundOn()) {
			Sound.menu.play();
			Sound.menu.setRepeat(true);
		}

		// remove all layers
		this.layers.removeAll();
		// show main menu
		this.showMenu();
		// disable main menu
		this.layers.getComponentsInLayer(1)[0].setEnabled(false);
		// show username box
		this.panel = this.addMenu(new UsernameMenu(), 2);
		((UsernameMenu) this.panel).getUserfield().requestFocusInWindow();
		((UsernameMenu) this.panel).getUserfield().selectAll();
	}

	public void startGame() {
		// remove all layers
		this.layers.removeAll();
		// paint background
		this.setUiBackground();
		// create game panel and add
		this.panel = new GamePanel();
		this.addMenu(this.panel, 1);
		// set viewport for the manager
		GameManager.setState(State.Run);
		GameWorld.makeLevel(1);
		GameManager.getManager().setViewport(this.panel);
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

	public void showPause() {
		this.addMenu(new PauseMenu(), 3);
	}

	/*
	 * 
	 * ONLINE MENUS
	 */
	public void showOnline() {
		this.addMenu(new OnlineMenu(), 2);
	}

	public void showJoin() {
		this.layers.remove(this.layers.getComponentsInLayer(2)[0]);
		this.addMenu(new JoinMenu(), 2);

	}

	public void showHost() {
		this.layers.remove(this.layers.getComponentsInLayer(2)[0]);
		this.addMenu(new HostMenu(), 2);

	}

	public void showOnlineError(String error) {
		this.addMenu(new OnlineErrorMenu(error), 4);
	}

	public void showWaitMenu() {
		WaitMenu.lobby = (WaitMenu) addMenu(new WaitMenu(), 3);
	}

	public void startClient(Client client) {
		// remove all layers
		this.layers.removeAll();
		// paint background
		this.setUiBackground();
		// create game panel and add
		this.panel = new GamePanel();
		this.addMenu(this.panel, 1);
		client.setPanel(this.panel);
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