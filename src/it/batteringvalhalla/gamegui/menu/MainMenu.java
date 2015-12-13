package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;
import it.batteringvalhalla.gamegui.sound.Sound;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private GameFrame frame;
	private ManagerFilePlayer mfp;
	private GridBagConstraints constraints;
	private JButtonCustom play;
	private JButtonRound options;
	private JButtonRound editor;
	private JButtonRound exit;
	private JLabel header;

	private int width = 1024;
	private int height = 768;

	public MainMenu() {
		super(new GridBagLayout());
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width,
				height);
		setOpaque(false);
		constraints = new GridBagConstraints();
		play = new JButtonCustom(ResourcesLoader.mainmenu_images.get(0),
				ResourcesLoader.mainmenu_images.get(1),
				ResourcesLoader.mainmenu_images.get(2));
		options = new JButtonRound(ResourcesLoader.mainmenu_images.get(3),
				ResourcesLoader.mainmenu_images.get(4));
		editor = new JButtonRound(ResourcesLoader.mainmenu_images.get(5),
				ResourcesLoader.mainmenu_images.get(6));
		exit = new JButtonRound(ResourcesLoader.mainmenu_images.get(7),
				ResourcesLoader.mainmenu_images.get(8));
		header = new JLabel("Battering Valhalla");
		header.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 144));

		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 2;
		add(header, constraints);

		constraints.weighty = 0.6;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		add(play, constraints);

		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.insets = new Insets(0, 8, 36, 8);
		add(options, constraints);

		constraints.gridx = 3;
		add(editor, constraints);

		constraints.gridx = 4;
		constraints.insets = new Insets(0, 8, 36, 16);
		add(exit, constraints);

		setVisible(true);
		listenerLoader();
	}

	private void listenerLoader() {
		play.addActionListener(e -> {
			if (ManagerFilePlayer.soundOn()) {
				Sound.button().setFramePosition(0);
				Sound.button().start();
			}
			setEnabled(false);
			frame.startGame();
		});
		options.addActionListener(e -> {
			if (ManagerFilePlayer.soundOn()) {
				Sound.button().setFramePosition(0);
				Sound.button().start();
			}
			setEnabled(false);
			frame.showOptions();
		});
		editor.addActionListener(e -> {
			if (ManagerFilePlayer.soundOn()) {
				Sound.button().setFramePosition(0);
				Sound.button().start();
			}
			setEnabled(false);
			frame.showEditor();
		});
		exit.addActionListener(e -> {
			if (ManagerFilePlayer.soundOn()) {
				Sound.button().setFramePosition(0);
				Sound.button().start();
			}
			setEnabled(false);
			frame.showExit();
		});

	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		play.setEnabled(enabled);
		options.setEnabled(enabled);
		editor.setEnabled(enabled);
		exit.setEnabled(enabled);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
