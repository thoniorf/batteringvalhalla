package it.thoniorf.entity;

import java.awt.Polygon;
import java.awt.Rectangle;

public interface MovableActor {
	public Integer getX();

	public void setX(Integer x);

	public Integer getY();

	public void setY(Integer y);

	public Integer getHealth();

	public void setHealth(Integer health);

	public Integer attack();

	public String getSprite();

	public void setHor(Integer hor);

	public void setVer(Integer ver);

	public Rectangle getBounds();

	public void update(Polygon border);

}
