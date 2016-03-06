package it.batteringvalhalla.gamegui.menu;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;
import it.batteringvalhalla.gamegui.menu.customcomponents.JCustomComboBox;

public class ArcadeMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Dimension size = new Dimension(600, 400);

	private JComboBox<String> maps;
	private JButtonCustom play;
	private JButtonRound exit;

	public ArcadeMenu() {
		super(null);
		setBounds(CenterComp.centerX(size.width), CenterComp.centerY(size.height), size.width, size.height);
		setOpaque(false);
		// initialization
		play = new JButtonCustom(ResourcesLoader.images.get("play"), ResourcesLoader.images.get("play"),
				ResourcesLoader.images.get("play"));
		exit = new JButtonRound(ResourcesLoader.images.get("exit_round"),
				ResourcesLoader.images.get("exit_round_hover"));
		maps = new JCustomComboBox(ManagerFilePlayer.loadNameOfMaps());
		// setting bounds
		maps.setBounds(101, 155, 391, 60);
		play.setBounds(108, 245, play.getWidth(), play.getHeight());
		exit.setBounds(516, 316, exit.getWidth(), exit.getHeight());
		// adding
		add(maps);
		add(play);
		add(exit);

		setVisible(true);
		listenerLoader();
	}

	private void listenerLoader() {
		play.addActionListener(e -> {
			setEnabled(false);
			GameWorld.setCustomLevel((String) maps.getSelectedItem());
			GameFrame.instance().startGame();
		});

		exit.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().restart();
		});
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		maps.setEnabled(enabled);
		play.setEnabled(enabled);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.images.get("background_6x4"), 0, 0, null);
		g.drawImage(ResourcesLoader.images.get("arcade_header"), 47, 20, null);
		g.drawImage(ResourcesLoader.images.get("maplist_header"), 72, 135, null);
	}

}
