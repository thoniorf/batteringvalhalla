package it.batteringvalhalla.gamecore.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InputHandler implements KeyListener {

	List<Boolean> keys;

	public InputHandler() {
		keys = new CopyOnWriteArrayList<Boolean>();
		for (int i = 0; i < 5; i++)
			keys.add(false);
	}

	public void resetKeys() {
		for (Boolean key : keys)
			key = false;
	}

	public List<Boolean> getKeys() {
		return keys;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			keys.set(0, true);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			keys.set(1, true);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			keys.set(2, true);
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			keys.set(3, true);
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			keys.set(4, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			keys.set(0, false);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			keys.set(1, false);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			keys.set(2, false);
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			keys.set(3, false);
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			keys.set(4, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
