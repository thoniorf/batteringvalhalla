package it.batteringvalhalla.gamegui.menu;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.State;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.network.Server;
import it.batteringvalhalla.gamecore.network.ServerStatus;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;

public class PauseMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private static Dimension size = new Dimension(600, 400);

    private JButtonCustom resume;
    private JButtonCustom exit;

    public PauseMenu() {
	super(null);
	setBounds(CenterComp.centerX(size.width), CenterComp.centerY(size.height), size.width, size.height);
	setOpaque(false);
	// initialization
	resume = new JButtonCustom(ResourcesLoader.images.get("resume"), ResourcesLoader.images.get("resume_hover"),
		ResourcesLoader.images.get("resume_selected"));
	exit = new JButtonCustom(ResourcesLoader.images.get("exit_big"), ResourcesLoader.images.get("exit_big_hover"),
		ResourcesLoader.images.get("exit_big_selected"));
	// setting bounds
	resume.setBounds(108, 137, resume.getWidth(), resume.getHeight());
	exit.setBounds(108, 245, exit.getWidth(), exit.getHeight());
	// add to panel
	add(resume);
	add(exit);

	setVisible(true);
	listenerLoader();
    }

    private void listenerLoader() {
	resume.addActionListener(e -> {
	    setEnabled(false);
	    GameFrame.instance().getLayeredPane()
		    .remove(GameFrame.instance().getLayeredPane().getComponentsInLayer(3)[0]);
	    GameFrame.instance().repaint();
	    GameManager.setState(State.Run);
	});
	exit.addActionListener(e -> {
	    setEnabled(false);
	    Server.status = ServerStatus.STOP;

	    GameFrame.instance().restart();
	});

    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(ResourcesLoader.images.get("background_6x4"), 0, 0, null);
	g.drawImage(ResourcesLoader.images.get("pause_header"), 47, 20, null);
    }

    @Override
    public void setEnabled(boolean enabled) {
	super.setEnabled(enabled);
	resume.setEnabled(enabled);
	exit.setEnabled(enabled);

    }
}
