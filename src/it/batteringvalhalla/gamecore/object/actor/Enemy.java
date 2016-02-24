package it.batteringvalhalla.gamecore.object.actor;

import java.awt.Point;

import it.batteringvalhalla.gamecore.ia.AbstractIA;

public class Enemy extends AbstractActor {

	public Enemy(Point origin, AbstractIA strategy) {
		super(origin, 0, 0, 0);
		this.strategy = strategy;
	}

	public Enemy(Point origin) {
		super(origin, 0, 0, 0);
		this.strategy = null;
	}

	public void setStrategy(AbstractIA strategy) {
		this.strategy = strategy;
	}

	public AbstractIA getStrategy() {
		return strategy;
	}

}
