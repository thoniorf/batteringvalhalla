package it.batteringvalhalla.gamegui.sound;

import jaco.mp3.player.MP3Player;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {

	public static MP3Player battle;
	public static AudioClip button;
	public static AudioClip collision;
	public static MP3Player menu;

	public Sound() {

	}

	public static synchronized void loadSound() {
		try {
			battle = new MP3Player(
					new URL(
							Sound.class
									.getClassLoader()
									.getResource(
											"it/batteringvalhalla/assets/sound/battle.mp3")
									.toString()));

			button = Applet
					.newAudioClip(new URL(
							Sound.class
									.getClassLoader()
									.getResource(
											"it/batteringvalhalla/assets/sound/button.wav")
									.toString()));

			collision = Applet.newAudioClip(new URL(Sound.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/sound/collision.wav")
					.toString()));

			menu = new MP3Player(new URL(Sound.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/sound/menu-theme.mp3")
					.toString()));

		} catch (MalformedURLException e) {

			battle = new MP3Player(
					new File(
							Sound.class
									.getClassLoader()
									.getResource(
											"it/batteringvalhalla/assets/sound/battle.mp3")
									.getPath()));

			menu = new MP3Player(new File(Sound.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/sound/menu-theme.mp3")
					.getPath()));
		}
	}
}
