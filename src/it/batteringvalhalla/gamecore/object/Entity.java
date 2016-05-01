package it.batteringvalhalla.gamecore.object;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

public interface Entity extends Serializable {
	public Boolean getAlive();

	public Shape getShape();

	public void setAlive(Boolean alive);

	public Point getOrigin();

	public int getX();

	public int getY();

	public Integer getWidth();

	public Integer getHeight();

	public void paint(Graphics2D g);

	public void update();
}
