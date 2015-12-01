package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamegui.editorActor.ImageEditor;

public class Player extends Actor {
	public static String username;
	public static Integer score = 0;

	public Player(int x, int y) {
		super(x, y, ImageEditor.getIndexTesta(), ImageEditor.getIndexBusto(), ImageEditor.getIndexCapra());
		setDirection(Direction.est);
		this.collider.setBounds(this.x, this.y, width, height);
	}
}
