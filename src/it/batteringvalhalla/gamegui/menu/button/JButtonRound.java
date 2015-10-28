package it.batteringvalhalla.gamegui.menu.button;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;

import javafx.scene.shape.Ellipse;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.LayoutStyle;
import javax.swing.text.LayoutQueue;


public class JButtonRound extends JButton {
	private Image image;
	private Image imageHover;
	private Image draw;
	private Circle2D circle;
	private Integer x;
	private Integer y;
	
	

	public JButtonRound(Image image,Image imageHover) {
		setContentAreaFilled(false);
		setOpaque(false);
		this.x=(int)this.getLocation().getX();
		this.y=(int)this.getLocation().getY();
		System.out.println(x+" "+y);
		this.image=image;
		this.imageHover=imageHover;
		this.draw=image;
		this.circle=new Circle2D(x,y,image.getWidth(null)/2);
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				if(circle.contains(e.getX(), e.getY())){
					draw=imageHover;
				}else {
					draw=image;
				}
				repaint();
			}});
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawImage(draw,x , y, null);
	}

	

}
