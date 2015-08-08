package it.batteringvalhalla.gamegui.progress;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.sound.Sound;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoadProgress extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int MAX = 100;
	ImageIcon loadspin;
	URL imageURL = ResourcesLoader.class.getClassLoader().getResource(
			"it/batteringvalhalla/assets/loadProgress/preloader.gif");

	public LoadProgress(GameFrame f) {
		loadspin = new ImageIcon(imageURL);
		loadResources();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 72));
		g.drawString("Loading", 350, 649);
		if (imageURL != null) {
			loadspin.paintIcon(this, g, 629, 569);
		}

	}

	public void run() {
		for (int i = 0; i < MAX; i++) {
			try {
				repaint();
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}

	private synchronized void loadResources() {
		try {
			ResourcesLoader.loadFont();
			ResourcesLoader.loadPlayerImages();
			ResourcesLoader.loadMainMenuImages();
			ResourcesLoader.loadScoreBoardImages();
			ResourcesLoader.loadExitMenuImages();
			ResourcesLoader.loadOptionMenuImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sound.loadSound();
	}
}
