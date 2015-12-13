package it.batteringvalhalla.gamegui;

public class CenterComp {

	public CenterComp() {
	}

	public static int centerX(int width) {
		if (width == 0 || width > GameFrame.instance().getScreenWidth())
			width = GameFrame.instance().getScreenWidth();
		return GameFrame.instance().getScreenWidth() / 2 - width / 2;
	}

	public static int centerY(int height) {
		if (height == 0 || height > GameFrame.instance().getScreenHeight())
			height = GameFrame.instance().getScreenHeight();
		return GameFrame.instance().getScreenHeight() / 2 - height / 2;
	}

}
