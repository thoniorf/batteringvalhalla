package it.batteringvalhalla.gamecore.object.actor;

import java.util.Random;

import it.batteringvalhalla.gamecore.IA.AbstractIA;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;


public class Enemy extends Actor {
	
	public Enemy(int x, int y) {
		
		super(x, y, 0,
				 new Random().nextInt(ResourcesLoader.actor_weapon.size()),
				new Random().nextInt( ResourcesLoader.actor_mount.size()));
		setDirection(Direction.stop);
		this.collider.setBounds(this.x, this.y, width, height);
	
	}

	public void setIA(AbstractIA ia,int level) {
		strategy = ia;
		strategy.levelUp(level);
	}
}
