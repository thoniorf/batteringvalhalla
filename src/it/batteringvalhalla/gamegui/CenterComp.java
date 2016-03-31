package it.batteringvalhalla.gamegui;

public class CenterComp {

	public static int centerX(int width) {
		return (GameFrame.size.width / 2) - (width / 2);
	}

	public static int relativeCenterX(int width, int relativewidth) {
		return (relativewidth / 2) - (width / 2);
	}

	public static int centerY(int height) {
		return (GameFrame.size.height / 2) - (height / 2);
	}

}
