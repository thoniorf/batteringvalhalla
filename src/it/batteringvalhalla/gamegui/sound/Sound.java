package it.batteringvalhalla.gamegui.sound;

import jaco.mp3.player.MP3Player;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {

	public static MP3Player ok;

	public Sound() {

	}

	public static synchronized void loadSound() {
		try {
			ok = new MP3Player(new URL(Sound.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/sound/background.mp3")
					.toString()));
		} catch (MalformedURLException e) {
			ok = new MP3Player(new File(Sound.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/sound/background.mp3")
					.getPath()));
		}
	}
}
