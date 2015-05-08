package it.batteringvalhalla.gamecore.arena;

import java.awt.Graphics;

public class Arena {

	int x;
	int y;
	int height;
	int width;

	public Arena() {
		x = 120;
		y = 160;
		height = 500;
		width = 800;
	}

	public void paint(Graphics g) {
		g.drawRect(x, y, width, height);

	}

	public boolean control(int xa, int ya, int w, int h) {

		return xa + (w / 2) >= x && xa + (w / 2) <= x + width
				&& ya + (h / 2) >= y && ya + (h / 2) <= y + height;

	}

}
