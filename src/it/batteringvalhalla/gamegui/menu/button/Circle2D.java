package it.batteringvalhalla.gamegui.menu.button;


import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Circle2D  {
	private Integer radius;
	private Integer x;
	private Integer y;
	
	public Circle2D(int x,int y,int radius) {
		this.x=x;
		this.y=y;
		this.radius=radius;
	}

	public void setBounds2D(int x,int y,int radius){
		this.x=x;
		this.y=y;
		this.radius=radius;
	}
	public Rectangle2D getBounds2D() {
		return new Rectangle(x-radius, y-radius, radius*2, radius*2);
	}


	public int getHeight() {
		return radius;
	}

	
	public int getWidth() {
		return radius;
	}


	public int getX() {
		return x;
	}

	
	public int getY() {
		return y;
	}

	
	public boolean contains(int x,int y) {
		int diffX,diffY;
		diffX=Math.abs(this.x-x);
		diffY=Math.abs(this.y-y);
		if(Math.sqrt(diffX*diffX+diffY*diffY)<=radius){
			return true;
		}
		return false;
	}

	

	

	

}
