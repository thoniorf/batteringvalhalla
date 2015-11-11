package it.batteringvalhalla.gamegui.progress;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.sound.Sound;

public class Loading extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<Method> loaderMethods;
	private String status[] = { "Loading Please Wait", "Looking Up Barbarian Kilts",
			"Adding Randomly Mispeled Words Into Text", "Sharpening Swords", "Grrr. Bark. Bark. Grrr.",
			"Ensuring Everything Works Perfektly" };
	private int max = 6;
	private int current;

	public Loading(GameFrame f) {
		loaderMethods = new ArrayList<Method>();
		current = 0;
		try {
			ResourcesLoader.loadFont();
			loaderMethods.add(ResourcesLoader.class.getMethod("loadPlayerImages"));
			loaderMethods.add(ResourcesLoader.class.getMethod("loadMainMenuImages"));
			loaderMethods.add(ResourcesLoader.class.getMethod("loadScoreBoardImages"));
			loaderMethods.add(ResourcesLoader.class.getMethod("loadExitMenuImages"));
			loaderMethods.add(ResourcesLoader.class.getMethod("loadOptionMenuImages"));
			loaderMethods.add(Sound.class.getMethod("loadSound"));
		} catch (NoSuchMethodException | SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 54));
		g.drawString(status[current], 86, 704);

	}

	public synchronized void loadResources() {
		for (Method m : loaderMethods) {
			try {
				m.invoke(null);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(5000);
				current = (current + 1) % 6;
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
