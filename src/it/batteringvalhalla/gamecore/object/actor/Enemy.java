package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamecore.IA.AbstractIA;
import it.batteringvalhalla.gamegui.editorActor.ImageEditor;

public class Enemy extends Actor {

	public Enemy(int x, int y) {
		super(x, y, (int) Math.random() % (ImageEditor.getIndexTesta() + 1),
				(int) Math.random() % (ImageEditor.getIndexBusto() + 1),
				(int) (Math.random() % ImageEditor.getIndexCapra() + 1));
		setDirection(Direction.stop);
		this.collider.setBounds(this.x, this.y, width, height);
	}

	public void setIA(AbstractIA ia) {
		strategy = ia;
	}
}
