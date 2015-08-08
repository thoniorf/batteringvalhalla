package it.batteringvalhalla.gamegui.progress;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.sound.Sound;

import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LoadProgress extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int MIN = 0;
	final int MAX = 100;
	ImageIcon image;
	URL imageURL = ResourcesLoader.class.getClassLoader().getResource(

	"it/batteringvalhalla/assets/loadProgress/preloader.gif");

	public LoadProgress(GameFrame f) {
		if (imageURL != null) {
			image = new ImageIcon(imageURL);
		}

		load();
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 100));
		g.drawString("Loading...", 350, 400);
		if (imageURL != null) {
			image.paintIcon(this, g, 430, 450);
		}

	}

	public void run() {
		for (int i = MIN; i <= MAX; i++) {
			try {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
					}
				});
				java.lang.Thread.sleep(100);
			} catch (InterruptedException e) {
				;
			}
		}
	}

	public void load() {
		try {

			ResourcesLoader.loadPlayerImages();
			ResourcesLoader.loadMainMenuImages();
			ResourcesLoader.loadScoreBoardImages();
			ResourcesLoader.loadExitMenuImages();
			ResourcesLoader.loadOptionMenuImages();
			ResourcesLoader.loadFont();

		} catch (IOException e) {

			e.printStackTrace();
		}

		Sound.loadSound();
	}
}
