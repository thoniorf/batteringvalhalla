package it.batteringvalhalla.gamecore.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {

	private static InputHandler handler;
	private static boolean keys[] = { false, false, false, false, false, false };
	private static final int N_keys = 5;

	private InputHandler() {

	}

	public static InputHandler instance() {
		if (handler == null) {
			handler = new InputHandler();
		}
		return handler;
	}

	public static void resetKeys() {
		for (int i = 0; i <= N_keys; i++) {
			keys[i] = false;
		}

	}

	public static boolean[] getKeys() {
		return keys;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
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
		super.keyReleased(e);
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

}
