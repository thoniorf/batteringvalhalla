package it.batteringvalhalla.gamecore.object.actor;

import java.awt.Polygon;

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

	public void update(Polygon border);

}
