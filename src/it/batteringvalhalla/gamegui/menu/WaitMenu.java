package it.batteringvalhalla.gamegui.menu;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class WaitMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Dimension size = new Dimension(600, 400);

	private String connected;
	private JButtonRound exit;

	public WaitMenu() {
		super(null);
		setBounds(CenterComp.centerX(size.width), CenterComp.centerY(size.height), size.width, size.height);
		setOpaque(false);
		// initialization
		this.connected = "Waiting other player...";
		this.exit = new JButtonRound(ResourcesLoader.images.get("exit_round"),
				ResourcesLoader.images.get("exit_round_hover"));

		// setting bounds
		this.exit.setBounds(516, 316, this.exit.getWidth(), this.exit.getHeight());
		// adding
		add(this.exit);

		setVisible(true);
		listenerLoader();
	}

	private void listenerLoader() {

		this.exit.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().restart();
		});
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.images.get("background_6x4"), 0, 0, null);
		g.drawImage(ResourcesLoader.images.get("join_header"), 47, 20, null);
		g.drawString(this.connected, 72, 135);
	}

}
