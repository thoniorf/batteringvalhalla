package it.batteringvalhalla.gamegui.menu;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class EditorsMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Dimension size = new Dimension(600, 400);

	private JButtonCustom player_editor;
	private JButtonCustom map_editor;
	private JButtonRound exit;

	public EditorsMenu() {
		super(null);
		setBounds(CenterComp.centerX(size.width), CenterComp.centerY(size.height), size.width, size.height);
		setOpaque(false);
		// initialization
		player_editor = new JButtonCustom(ResourcesLoader.images.get("player_editor"),
				ResourcesLoader.images.get("player_editor_hover"),
				ResourcesLoader.images.get("player_editor_selected"));
		map_editor = new JButtonCustom(ResourcesLoader.images.get("map_editor"),
				ResourcesLoader.images.get("map_editor_hover"), ResourcesLoader.images.get("map_editor_selected"));
		exit = new JButtonRound(ResourcesLoader.images.get("exit_round"),
				ResourcesLoader.images.get("exit_round_hover"));
		// setting bounds
		player_editor.setBounds(108, 137, player_editor.getWidth(), player_editor.getHeight());
		map_editor.setBounds(108, 245, map_editor.getWidth(), map_editor.getHeight());
		exit.setBounds(516, 316, exit.getWidth(), exit.getHeight());
		// add to panel
		add(player_editor);
		add(map_editor);
		add(exit);

		setVisible(true);
		listenerLoader();
	}

	private void listenerLoader() {
		player_editor.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().showEditor();
		});
		map_editor.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().showEditorMap();
		});

		exit.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().restart();
		});
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		player_editor.setEnabled(enabled);
		map_editor.setEnabled(enabled);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.images.get("background_6x4"), 0, 0, null);
		g.drawImage(ResourcesLoader.images.get("editors_header"), 47, 20, null);
	}
}
