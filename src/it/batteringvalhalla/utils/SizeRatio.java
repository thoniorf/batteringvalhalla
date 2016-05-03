package it.batteringvalhalla.utils;

import java.awt.Dimension;

import it.batteringvalhalla.gamecore.vector2d.Vector2D;
import it.batteringvalhalla.gamegui.GameFrame;

public class SizeRatio {

	private static Dimension display = GameFrame.size;
	private static final Dimension reference = new Dimension(1024, 768);

	public static Vector2D ratio(Vector2D source) {
		return new Vector2D((source.getComponents().x * display.width) / reference.width,
				(source.getComponents().y * display.height) / reference.height);
	}

}
