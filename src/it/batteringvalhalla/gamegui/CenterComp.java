package it.batteringvalhalla.gamegui;

public class CenterComp {

	public CenterComp() {
	}

	public static int centerX(int width) {
		if (width == 0 || width > GameFrame.instance().getScreen_width())
			width = GameFrame.instance().getScreen_width();
		return GameFrame.instance().getScreen_width() / 2 - width / 2;
	}

	public static int centerY(int height) {
		if (height == 0 || height > GameFrame.instance().getScreen_height())
			height = GameFrame.instance().getScreen_height();
		return GameFrame.instance().getScreen_height() / 2 - height / 2;
	}

}
