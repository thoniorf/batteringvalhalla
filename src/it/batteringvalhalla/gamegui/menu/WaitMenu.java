package it.batteringvalhalla.gamegui.menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.network.ConnectionManager;
import it.batteringvalhalla.gamecore.network.Server;
import it.batteringvalhalla.gamecore.network.ServerStatus;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class WaitMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private static Dimension size = new Dimension(600, 400);
    private static List<String> players = new ArrayList<String>();
    public static WaitMenu lobby;

    public static void setPlayer(String player) {
	players.add(player);
	lobby.repaint();
    }

    private JButtonRound exit;

    public WaitMenu() {
	super(null);
	setPreferredSize(size);
	setBounds(CenterComp.centerX(size.width), CenterComp.centerY(size.height), size.width, size.height);
	setOpaque(false);
	// initialization
	this.exit = new JButtonRound(ResourcesLoader.images.get("exit_round"),
		ResourcesLoader.images.get("exit_round_hover"));
	// setting bounds
	this.exit.setBounds(516, 316, this.exit.getWidth(), this.exit.getHeight());
	// adding
	add(this.exit);
	setVisible(true);
	listenerLoader();
	players = new ArrayList<String>();
    }

    private void listenerLoader() {

	this.exit.addActionListener(e -> {
	    setEnabled(false);
	    Server.status = ServerStatus.STOP;
	    try {
		ConnectionManager.getServerSocket().close();
	    } catch (Exception e1) {
		e1.printStackTrace();
	    }
	    GameFrame.instance().restart();
	});
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(ResourcesLoader.images.get("background_6x4"), 0, 0, null);
	g.drawImage(ResourcesLoader.images.get("connecting_header"), 47, 20, null);
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 40));
	for (int i = 0; i < players.size(); i++) {
	    g.drawString(players.get(i) + ", Connected !", 47, 173 + (45 * i));
	}
    }

    @Override
    public void setEnabled(boolean enabled) {
	super.setEnabled(enabled);

    }

}
