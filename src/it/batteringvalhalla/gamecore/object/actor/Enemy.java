package it.batteringvalhalla.gamecore.object.actor;

import java.awt.Point;
import java.util.Random;

import it.batteringvalhalla.gamecore.ia.AbstractIA;

public class Enemy extends AbstractActor {

	public Enemy(Point origin, AbstractIA strategy) {
		super(origin, new Random().nextInt(1), 0, new Random().nextInt(1));
		this.strategy = strategy;
	}

	public Enemy(Point origin) {
		super(origin, new Random().nextInt(1), 0, new Random().nextInt(1));
		this.strategy = null;
	}

	public void setStrategy(AbstractIA strategy) {
		this.strategy = strategy;
	}

	public AbstractIA getStrategy() {
		return strategy;
	}

}
