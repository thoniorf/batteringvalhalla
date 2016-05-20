package it.batteringvalhalla.gamegui.menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.network.Client;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class JoinMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Dimension size = new Dimension(600, 600);

	private JTextField hostname;
	private JTextField port;
	private JButtonCustom join;
	private JButtonRound exit;

	public JoinMenu() {
		super(null);
		setBounds(CenterComp.centerX(size.width), CenterComp.centerY(size.height), size.width, size.height);
		setOpaque(false);
		// initialization
		join = new JButtonCustom(ResourcesLoader.images.get("join"), ResourcesLoader.images.get("join_hover"),
				ResourcesLoader.images.get("join_selected"));
		exit = new JButtonRound(ResourcesLoader.images.get("exit_round"),
				ResourcesLoader.images.get("exit_round_hover"));
		hostname = new JTextField();
		this.port = new JTextField("46505");
		this.port.setBorder(null);
		this.port.setOpaque(false);
		this.port.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 52));
		this.port.setHorizontalAlignment(JTextField.CENTER);
		hostname.setBorder(null);
		hostname.setOpaque(false);
		hostname.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 54));
		hostname.setHorizontalAlignment(JTextField.CENTER);
		hostname.setText("127.0.0.1");
		// setting bounds
		hostname.setBounds(101, 155, 391, 60);
		this.port.setBounds(197, 340, 165, 60);
		join.setBounds(108, 438, join.getWidth(), join.getHeight());
		exit.setBounds(516, 516, exit.getWidth(), exit.getHeight());
		// adding
		add(hostname);
		add(join);
		add(port);
		add(exit);

		setVisible(true);
		listenerLoader();
	}

	private void listenerLoader() {
		join.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().showWaitMenu();
			Client.port = Integer.parseInt(port.getText());
			Client client = new Client(hostname.getText());
			new Thread(client).start();
		});

		exit.addActionListener(e -> {
			setEnabled(false);
			GameFrame.instance().restart();
		});
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		hostname.setEnabled(enabled);
		join.setEnabled(enabled);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.images.get("background_6x6"), 0, 0, null);
		g.drawImage(ResourcesLoader.images.get("join_header"), 47, 20, null);
		g.drawImage(ResourcesLoader.images.get("port_header"), 188, 263, null);
		g.drawImage(ResourcesLoader.images.get("maplist_header"), 72, 135, null);
	}

}
