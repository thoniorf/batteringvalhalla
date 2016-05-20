package it.batteringvalhalla.gamegui.menu;

import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.State;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class OnlineErrorMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints;
	private GameFrame frame;
	private ManagerFilePlayer mfp;
	private JButtonRound no;

	private String errortype;

	private int width = 512;
	private int height = 256;

	public OnlineErrorMenu(String error) {
		super(null);
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
		setOpaque(false);
		// initialize
		no = new JButtonRound(ResourcesLoader.images.get("exit_blue"), ResourcesLoader.images.get("exit_blue_hover"));
		// setting bounds
		no.setBounds(206, 128, no.getWidth(), no.getHeight());
		add(no);
		setVisible(true);
		listenerLoader();
		this.errortype = error;
	}

	private void listenerLoader() {
		no.addActionListener(e -> {
			GameManager.setState(State.Stop);
			frame.restart();
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.images.get("background_5x2"), 0, 0, null);
		g.drawImage(ResourcesLoader.images.get(errortype + "_header"), 30, 20, null);
	}

}
