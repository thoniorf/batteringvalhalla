package it.batteringvalhalla.gamegui.menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private GameFrame frame;
	private ManagerFilePlayer mfp;
	private GridBagConstraints constraints;
	private JButtonRound play;
	private JButtonRound options;
	private JButtonRound editor;
	private JButtonRound exit;

	public MainMenu() {
		super(new GridBagLayout());
		this.frame = GameFrame.instance();
		constraints = new GridBagConstraints();
		play = new JButtonRound(ResourcesLoader.mainmenu_images.get(0), ResourcesLoader.mainmenu_images.get(1));
		options = new JButtonRound(ResourcesLoader.mainmenu_images.get(2), ResourcesLoader.mainmenu_images.get(3));
		editor = new JButtonRound(ResourcesLoader.mainmenu_images.get(4), ResourcesLoader.mainmenu_images.get(5));
		exit = new JButtonRound(ResourcesLoader.mainmenu_images.get(6), ResourcesLoader.mainmenu_images.get(7));

		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.weighty = 0.8;
		add(play);
		add(options);
		add(editor);
		add(exit);

		setVisible(true);

		listenerLoader();
	}

	private void listenerLoader() {
		play.addActionListener(e -> {
			frame.startGame();
		});
		options.addActionListener(e -> {
			frame.showOptions();
		});
		editor.addActionListener(e -> {
			frame.showEditor();
		});
		exit.addActionListener(e -> {
			frame.showExit();
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 144));
		g.drawString("Battering Valhalla", 39, 64);
	}

}
