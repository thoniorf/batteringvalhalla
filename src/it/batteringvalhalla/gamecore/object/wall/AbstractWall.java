package it.batteringvalhalla.gamecore.object.wall;

import it.batteringvalhalla.gamecore.object.AbstractGameObject;

import java.awt.Graphics2D;
import java.awt.Polygon;

public abstract class AbstractWall extends AbstractGameObject {
	protected Integer x;
	protected Integer y;
	protected Integer width;
	protected Integer height;
	protected Double theta;
	protected Integer corner;

	public AbstractWall(Integer x, Integer y, Integer width, Integer height,
			Integer corner) {
		super(x, y);
		this.y = y;
		this.x = x;
		this.width = width;
		this.height = height;
		this.theta = Math.toRadians(corner);
		this.corner = corner;
	}

	public Polygon getBounds() {
		Polygon p = new Polygon();
		if (this.corner == 45) {
			p.addPoint(this.x, this.y);
			p.addPoint((this.x - this.width / 2), this.y + (this.width / 2));
			p.addPoint(((this.height / 2) + this.x) - (this.width / 2),
					(this.height / 2) + this.y + (this.width / 2));
			p.addPoint((this.height / 2) + this.x, (this.height / 2) + this.y);
		}

		else if (this.corner == 135) {
			p.addPoint(this.x, this.y);
			p.addPoint((this.x + this.width / 2), this.y + (this.width / 2));
			p.addPoint((this.x - (this.height / 2)) + (this.width / 2),
					(this.height / 2) + this.y + (this.width / 2));
			p.addPoint(this.x - (this.height / 2), (this.height / 2) + this.y);
		} else if (this.corner == 0) {
			p.addPoint(this.x, this.y);
			p.addPoint(this.height + this.x, y);
			p.addPoint(this.height + this.x, this.y + this.width);
			p.addPoint(x, this.y + this.width);
		} else if (this.corner == 90) {
			p.addPoint(this.x, this.y);
			p.addPoint(this.width + this.x, y);
			p.addPoint(this.width + this.x, this.y + this.height);
			p.addPoint(x, this.y + this.height);
		}
		return p;
	}

	public abstract void paint(Graphics2D g2d);

	public abstract void postCollision(/* Game.Object boh */);
}