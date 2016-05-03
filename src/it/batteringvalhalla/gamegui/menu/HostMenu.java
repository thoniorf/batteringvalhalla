package it.batteringvalhalla.gamegui.menu;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.network.Client;
import it.batteringvalhalla.gamecore.network.ConnectionManager;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;
import it.batteringvalhalla.gamegui.menu.customcomponents.JCustomComboBox;

public class HostMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Dimension size = new Dimension(600, 400);

	private JComboBox<String> maps;
	private JButtonCustom host;
	private JButtonRound exit;

	public HostMenu() {
		super(null);
		setBounds(CenterComp.centerX(size.width), CenterComp.centerY(size.height), size.width, size.height);
		setOpaque(false);
		// initialization
		this.host = new JButtonCustom(ResourcesLoader.images.get("host"), ResourcesLoader.images.get("host_hover"),
				ResourcesLoader.images.get("host_selected"));
		this.exit = new JButtonRound(ResourcesLoader.images.get("exit_round"),
				ResourcesLoader.images.get("exit_round_hover"));
		this.maps = new JCustomComboBox(ManagerFilePlayer.loadNameOfMaps());
		// setting bounds
		this.maps.setBounds(101, 155, 391, 60);
		this.host.setBounds(108, 245, this.host.getWidth(), this.host.getHeight());
		this.exit.setBounds(516, 316, this.exit.getWidth(), this.exit.getHeight());
		// adding
		add(this.maps);
		add(this.host);
		add(this.exit);

		setVisible(true);
		listenerLoader();
	}

	private void listenerLoader() {
		this.host.addActionListener(e -> {
			setEnabled(false);
			GameWorld.setLevelName((String) this.maps.getSelectedItem());
			GameWorld.makeLevel(0);
			new Thread(new ConnectionManager()).start();
			GameFrame.instance().startClient(new Client("127.0.0.1"));
		});

		this.exit.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().restart();
		});
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		this.maps.setEnabled(enabled);
		this.host.setEnabled(enabled);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.images.get("background_6x4"), 0, 0, null);
		g.drawImage(ResourcesLoader.images.get("host_header"), 47, 20, null);
		g.drawImage(ResourcesLoader.images.get("maplist_header"), 72, 135, null);
	}

}
