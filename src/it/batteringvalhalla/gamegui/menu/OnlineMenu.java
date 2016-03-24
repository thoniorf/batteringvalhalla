package it.batteringvalhalla.gamegui.menu;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class OnlineMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Dimension size = new Dimension(600, 400);

	private JButtonCustom join;
	private JButtonCustom host;
	private JButtonRound exit;

	public OnlineMenu() {
		super(null);
		setBounds(CenterComp.centerX(size.width), CenterComp.centerY(size.height), size.width, size.height);
		setOpaque(false);
		// initialization
		join = new JButtonCustom(ResourcesLoader.images.get("join"), ResourcesLoader.images.get("join_hover"),
				ResourcesLoader.images.get("join_selected"));
		host = new JButtonCustom(ResourcesLoader.images.get("host"), ResourcesLoader.images.get("host_hover"),
				ResourcesLoader.images.get("host_selected"));
		exit = new JButtonRound(ResourcesLoader.images.get("exit_round"),
				ResourcesLoader.images.get("exit_round_hover"));
		// setting bounds
		join.setBounds(108, 137, join.getWidth(), join.getHeight());
		host.setBounds(108, 245, host.getWidth(), host.getHeight());
		exit.setBounds(516, 316, exit.getWidth(), exit.getHeight());
		// adding
		// add to panel
		add(join);
		add(host);
		add(exit);

		setVisible(true);
		listenerLoader();
	}

	private void listenerLoader() {
		join.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().showJoin();
		});
		host.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().showHost();
		});
		exit.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().restart();
		});

	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		join.setEnabled(enabled);
		host.setEnabled(enabled);
		exit.setEnabled(enabled);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.images.get("background_6x4"), 0, 0, null);
		g.drawImage(ResourcesLoader.images.get("online_header"), 47, 20, null);
	}
}
