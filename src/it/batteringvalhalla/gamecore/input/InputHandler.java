package it.batteringvalhalla.gamecore.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	boolean keys[] = { false, false, false, false, false, false };
	private static int N_keys = 6;

	public InputHandler() {
	}

	public void resetKeys() {
		for (int i = 0; i <= N_keys; i++) {
			keys[i] = false;
		}

	}

	public boolean[] getKeys() {
		return keys;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			keys[0] = true;
		if (e.getKeyCode() == KeyEvent.VK_S)
			keys[1] = true;
		if (e.getKeyCode() == KeyEvent.VK_D)
			keys[2] = true;
		if (e.getKeyCode() == KeyEvent.VK_A)
			keys[3] = true;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			keys[4] = true;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			keys[5] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			keys[0] = false;
		if (e.getKeyCode() == KeyEvent.VK_S)
			keys[1] = false;
		if (e.getKeyCode() == KeyEvent.VK_D)
			keys[2] = false;
		if (e.getKeyCode() == KeyEvent.VK_A)
			keys[3] = false;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			keys[4] = false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			keys[5] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
