package it.batteringvalhalla.gamecore.object.wall;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public  class WallLives extends AbstractWall  {
	private Integer lives;
	
	
public WallLives(Integer x, Integer y, Integer width, Integer height, Integer corner,Integer lives) {
		super(x, y, width, height, corner);
			this.lives=lives;
			}

public void paint (Graphics2D g2d){
	Toolkit tk=Toolkit.getDefaultToolkit();
	Image image;
	if(corner==0){
		image=tk.getImage("");}
	else if(corner==90)
			image=tk.getImage("");
	else if(corner==45)
		image=tk.getImage("");
	else if(corner==135)
		image=tk.getImage("");
	else{
		image=tk.getImage("");
	}
	g2d.drawImage(image,this.x,this.y,null);
	
}


public boolean live() {
	if(lives>0)
		return false;
	return true;
}

@Override
public void postCollision(/*Game.Object boh*/) {
	lives--;
	
}

}
