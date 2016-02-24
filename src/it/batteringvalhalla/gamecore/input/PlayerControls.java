package it.batteringvalhalla.gamecore.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PlayerControls extends KeyAdapter {

	private static Map<String, Integer[]> key_map;

	public PlayerControls() {
		key_map = new HashMap<String, Integer[]>();
		key_map.put("W", new Integer[] { 0, KeyEvent.VK_W });
		key_map.put("A", new Integer[] { 0, KeyEvent.VK_A });
		key_map.put("D", new Integer[] { 0, KeyEvent.VK_D });
		key_map.put("S", new Integer[] { 0, KeyEvent.VK_S });
		key_map.put("ESCAPE", new Integer[] { 0, KeyEvent.VK_ESCAPE });
		key_map.put("SPACE", new Integer[] { 0, KeyEvent.VK_SPACE });
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		Iterator<Integer[]> it = key_map.values().iterator();
		while (it.hasNext()) {
			Integer[] value = it.next();
			if (value[1].equals(e.getKeyCode())) {
				value[0] = 1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		Iterator<Integer[]> it = key_map.values().iterator();
		while (it.hasNext()) {
			Integer[] value = it.next();
			if (value[1].equals(e.getKeyCode())) {
				value[0] = 0;
			}
		}
	}

	public static Map<String, Integer[]> getKeys() {
		return Collections.unmodifiableMap(key_map);
	}
}
