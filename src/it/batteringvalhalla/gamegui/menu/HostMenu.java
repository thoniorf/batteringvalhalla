package it.batteringvalhalla.gamegui.menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.network.Client;
import it.batteringvalhalla.gamecore.network.ConnectionManager;
import it.batteringvalhalla.gamecore.network.Server;
import it.batteringvalhalla.gamecore.network.ServerStatus;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;
import it.batteringvalhalla.gamegui.menu.customcomponents.JCustomComboBox;

public class HostMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private static Dimension size = new Dimension(600, 600);

    private JComboBox<String> maps;
    private JComboBox<String> number;
    private JTextField port;
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
	this.number = new JCustomComboBox(new String[] { "2", "3", "4" });
	this.port = new JTextField("46505");
	this.port.setBorder(null);
	this.port.setOpaque(false);
	this.port.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 52));
	this.port.setHorizontalAlignment(JTextField.CENTER);
	// setting bounds
	this.maps.setBounds(101, 155, 391, 60);
	this.number.setBounds(360, 340, 120, 60);
	this.port.setBounds(70, 340, 165, 60);
	this.host.setBounds(108, 438, this.host.getWidth(), this.host.getHeight());
	this.exit.setBounds(516, 516, this.exit.getWidth(), this.exit.getHeight());
	// adding
	add(this.maps);
	add(this.number);
	add(this.port);
	add(this.host);
	add(this.exit);

	setVisible(true);
	listenerLoader();
    }

    private void listenerLoader() {
	this.host.addActionListener(e -> {
	    setEnabled(false);
	    GameWorld.setLevelName((String) maps.getSelectedItem());
	    GameWorld.makeLevel(0);
	    Server.maxClients = Integer.parseInt((String) number.getSelectedItem());
	    ConnectionManager.port = Integer.parseInt(port.getText());
	    GameFrame.instance().showWaitMenu();
	    new Thread(new ConnectionManager()).start();
	    if (!ServerStatus.STOP.equals(Server.status)) {
		Client.port = ConnectionManager.port;
		Client client = new Client("127.0.0.1");
		new Thread(client).start();
	    }

	});

	this.exit.addActionListener(e -> {
	    setEnabled(false);
	    GameFrame.instance().restart();
	});
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(ResourcesLoader.images.get("background_6x6"), 0, 0, null);
	g.drawImage(ResourcesLoader.images.get("host_header"), 47, 20, null);
	g.drawImage(ResourcesLoader.images.get("port_header"), 61, 263, null);
	g.drawImage(ResourcesLoader.images.get("player_number_header"), 334, 263, null);
	g.drawImage(ResourcesLoader.images.get("maplist_header"), 72, 135, null);
    }

    @Override
    public void setEnabled(boolean enabled) {
	super.setEnabled(enabled);
	this.maps.setEnabled(enabled);
	this.host.setEnabled(enabled);

    }

}
