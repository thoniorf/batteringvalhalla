package it.batteringvalhalla.gamegui.menu;

import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.State;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.network.Server;
import it.batteringvalhalla.gamecore.network.ServerStatus;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class ExitMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private GridBagConstraints constraints;
    private GameFrame frame;
    private ManagerFilePlayer mfp;
    private JButtonRound no;
    private JButtonRound yes;

    private int width = 512;
    private int height = 256;

    public ExitMenu() {
	super(null);
	this.frame = GameFrame.instance();
	setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
	setOpaque(false);
	// initialize
	yes = new JButtonRound(ResourcesLoader.images.get("confirm_red"),
		ResourcesLoader.images.get("confirm_red_hover"));
	no = new JButtonRound(ResourcesLoader.images.get("exit_blue"), ResourcesLoader.images.get("exit_blue_hover"));
	// setting bounds
	yes.setBounds(105, 123, yes.getWidth(), yes.getHeight());
	no.setBounds(274, 123, no.getWidth(), no.getHeight());

	add(yes);
	add(no);
	setVisible(true);
	listenerLoader();
    }

    private void listenerLoader() {
	yes.addActionListener(e -> {
	    if (frame.getLayeredPane().getComponentsInLayer(1)[0] instanceof MainMenu) {
		Runtime.getRuntime().exit(0);
	    } else {
		GameManager.setState(State.Stop);
		Server.status = ServerStatus.STOP;
		frame.restart();
	    }
	});
	no.addActionListener(e -> {
	    if (frame.getLayeredPane().getComponentsInLayer(1)[0] instanceof MainMenu) {
		frame.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(true);
	    } else {
		GameManager.setState(State.Run);
	    }
	    frame.getLayeredPane().remove(frame.getLayeredPane().getComponentsInLayer(3)[0]);
	    frame.getLayeredPane().repaint();
	    frame.repaint();
	});

    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(ResourcesLoader.images.get("background_5x2"), 0, 0, null);
	g.drawImage(ResourcesLoader.images.get("exit_header"), 55, 20, null);
    }

}
