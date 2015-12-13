package it.batteringvalhalla.gamegui.sound;

import jaco.mp3.player.MP3Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	private static AudioInputStream audioIn;
	private static Clip clip;
	public static MP3Player battle;
	public static MP3Player menu;
	public static URL urlButton;
	public static URL urlCollision;

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

			urlButton = new URL(
					Sound.class
							.getClassLoader()
							.getResource(
									"it/batteringvalhalla/assets/sound/button.wav")
							.toString());

			urlCollision = new URL(Sound.class
					.getClassLoader()
					.getResource(
							"it/batteringvalhalla/assets/sound/collision.wav")
					.toString());

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

	public static Clip button() {
		try {
			audioIn = AudioSystem.getAudioInputStream(urlButton);
			clip = AudioSystem.getClip();
			clip.open(audioIn);

		} catch (UnsupportedAudioFileException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (LineUnavailableException e) {

			e.printStackTrace();
		}
		return clip;
	}

	public static Clip collision() {
		try {
			audioIn = AudioSystem.getAudioInputStream(urlCollision);
			clip = AudioSystem.getClip();
			clip.open(audioIn);

		} catch (UnsupportedAudioFileException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (LineUnavailableException e) {

			e.printStackTrace();
		}
		return clip;
	}

}
