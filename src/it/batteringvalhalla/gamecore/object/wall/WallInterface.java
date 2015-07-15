package it.batteringvalhalla.gamecore.object.wall;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface WallInterface {
public void paint (Graphics2D g2d);
public Rectangle getBounds();
public void postCollision(/*Game.Object boh*/);
	 
}

