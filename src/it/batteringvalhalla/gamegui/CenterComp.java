package it.batteringvalhalla.gamegui;

public class CenterComp {

	public CenterComp() {
	}

	public static int centerX(int width) {
		if (width == 0 || width > GameFrame.size.width)
			width = GameFrame.size.width;
		return GameFrame.size.width / 2 - width / 2;
	}

	public static int centerY(int height) {
		if (height == 0 || height > GameFrame.size.height)
			height = GameFrame.size.height;
		return GameFrame.size.height / 2 - height / 2;
	}

}
