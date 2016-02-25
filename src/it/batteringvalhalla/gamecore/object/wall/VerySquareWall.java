package it.batteringvalhalla.gamecore.object.wall;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.AbstractEntity;

public class VerySquareWall extends AbstractEntity{
	
	private static final Integer width=50;
	private static final Integer height=50;
	private Integer maxLife;
	private Integer life;
	private Image image;
	
	
	
	
	public VerySquareWall(Integer x, Integer y, Integer maxLife) {
		super(new Point(x,y));
		this.maxLife=maxLife;
		life=maxLife;
		if(maxLife==-1)
			image=ResourcesLoader.actor_mount.get(0).getScaledInstance(width, height, 0);
		else
			image=ResourcesLoader.actor_mount.get(1).getScaledInstance(width, height, 0);
	}



	public void paint(Graphics g) {
		
		g.drawImage(image, origin.x, origin.y,width,height,null );
		}
	public Rectangle getRectangle(){
		return new Rectangle(origin.x,origin.y,width,height);
	}
	
	public void postCollision() {
		
		if(maxLife>0){
			life--;
		}
	}
	


	public Integer getMaxLife() {
		return maxLife;
	}
	

	public void setX(double x) {
		this.origin.x=(int) x;
	}
	
	public void setY(double y) {
		this.origin.y=(int) y;
	}
	
	public Image getImage() {
		return image;
	}


	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}
