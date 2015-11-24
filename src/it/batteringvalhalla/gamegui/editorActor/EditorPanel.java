package it.batteringvalhalla.gamegui.editorActor;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.GameFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;



public class EditorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int HEIGHTSFONDO = 690;
	private static final int WIDTHSFONDO = 1024;
	private static final int HEIGHTFRECCIE = 50;
	private static final int WIDTHFRECCIE = 50;
	private static final int HEIGHTESTA = 100;
	private static final int WIDTHTESTA = 100;
	private static final int HEIGHTBUSTO = 100;
	private static final int WIDTHBUSTO = 100;
	private static final int HEIGHTCAPRA = 100;
	private static final int WIDTHCAPRA = 100;
	private static final int HEIGHTTASTI=80;
	private static final int WIDTHTASTI=80;
	private static final int x1=50;
	private static final int x2=900;
	private static final int y1=220;
	private static final int y2=320;
	private static final int y3=420;
	private static final int yTesta=200;
	private static final int yBusto=300;
	private static final int yCapra=400;
	private static final int xSave=80;
	private static final int xExit=920;
	private static final int yComandi=600;
	private static final int xImage=480;
	private Rectangle2D tasto1;
	private Rectangle2D tasto2;
	private Rectangle2D tasto3;
	private Rectangle2D tasto4;
	private Rectangle2D tasto5;
	private Rectangle2D tasto6;
	private Rectangle2D save;
	private Rectangle2D exit;
	private ImageEditor ie;
	private GameFrame gf;
	
	Font font;
	public EditorPanel (GameFrame gm){
		this.gf=gm;
		ie=new ImageEditor();
		setPreferredSize(new Dimension(WIDTHSFONDO,HEIGHTSFONDO));
		setBackground(Color.PINK);
		
		tasto1=new Rectangle(x1,y1,WIDTHFRECCIE,HEIGHTFRECCIE);
		tasto2=new Rectangle(x2,y1,WIDTHFRECCIE,HEIGHTFRECCIE);
		tasto3=new Rectangle(x1,y2,WIDTHFRECCIE,HEIGHTFRECCIE);
		tasto4=new Rectangle(x2,y2,WIDTHFRECCIE,HEIGHTFRECCIE);
		tasto5=new Rectangle(x1,y3,WIDTHFRECCIE,HEIGHTFRECCIE);
		tasto6=new Rectangle(x2,y3,WIDTHFRECCIE,HEIGHTFRECCIE);
	    exit=new Rectangle(xExit,yComandi,WIDTHTASTI,HEIGHTTASTI);
		save=new Rectangle(xSave,yComandi,WIDTHTASTI,HEIGHTTASTI);
		

		
	    repaint();
		addMouseListener( new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				resetBotton();
				changeImage(e.getX(),e.getY());
				repaint();
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				bottonChangeImage(e.getX(),e.getY());
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				resetBotton();
				bottonChangeImage(e.getX(),e.getY());
				
			}
			

			
			
			
			
			
		});
		
	}
private void resetBotton(){
	ie.resetExit();
	ie.resetSave();
}
private void bottonChangeImage(int x,int y){
	if(exit.contains(x, y)){
		ie.pushExit();}
	if(save.contains(x,y))
		ie.pushSave();
	repaint();
}
private void changeImage(int x,int y){
	resetBotton();
	if(tasto1.contains(x, y)){
		ie.spostaHead(-1);
		
	}
	
	if(tasto2.contains(x, y)){
		ie.spostaHead(1);
	}
	if(tasto3.contains(x, y)){
		ie.spostaBust(-1);
	}
	if(tasto4.contains(x, y)){
		ie.spostaBust(1);
	}
	if(tasto5.contains(x, y)){
		ie.spostaGoat(-1);
	}
	if(tasto6.contains(x, y)){
		ie.spostaGoat(1);
	}
	if(save.contains(x,y)){
		ManagerFilePlayer.setTop(ImageEditor.getIndexTesta());
		ManagerFilePlayer.setMid(ImageEditor.getIndexBusto());
		ManagerFilePlayer.setBot(ImageEditor.getIndexCapra());
		ManagerFilePlayer.save();
		
	}
	if(exit.contains(x, y)){
		ie.pushExit();
		Chiudere();
	}
	
}

public void paint(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	super.paint(g);
	g2d.drawImage(ie.getSfondo(),0,0,WIDTHSFONDO,HEIGHTSFONDO, null);
	g2d.drawImage(ie.getFrecciaSinistra(),x1, y1, WIDTHFRECCIE, HEIGHTFRECCIE, null);
	g2d.drawImage(ie.getFrecciaSinistra(),x1, y2, WIDTHFRECCIE, HEIGHTFRECCIE, null);
	g2d.drawImage(ie.getFrecciaSinistra(),x1, y3, WIDTHFRECCIE, HEIGHTFRECCIE, null);
	g2d.drawImage(ie.getFrecciaDestra(),x2, y1, WIDTHFRECCIE, HEIGHTFRECCIE, null);
	g2d.drawImage(ie.getFrecciaDestra(),x2, y2, WIDTHFRECCIE, HEIGHTFRECCIE, null);
	g2d.drawImage(ie.getFrecciaDestra(),x2, y3, WIDTHFRECCIE, HEIGHTFRECCIE, null);
	g2d.drawImage(ie.getImageHead(),xImage, yTesta, WIDTHTESTA, HEIGHTESTA, null);
	g2d.drawImage(ie.getImageBust(),xImage, yBusto, WIDTHBUSTO, HEIGHTBUSTO, null);
	g2d.drawImage(ie.getImageGoat(),xImage, yCapra, WIDTHCAPRA, HEIGHTCAPRA, null);
	g2d.drawImage(ie.getExit(), xExit, yComandi, WIDTHTASTI,HEIGHTTASTI,null);
	g2d.drawImage(ie.getSave(), xSave, yComandi, WIDTHTASTI,HEIGHTTASTI,null);
	g.setColor(Color.BLACK);
	g.setFont(new Font(ResourcesLoader.gothic.getName(),ResourcesLoader.gothic.getStyle(),130));
	g.drawString("Editor Valhalla", 50, 110);
}

	

public void Chiudere() {

	gf.menuStart();}

public void showExitConfirm() {
	
		System.exit(0);
	
}
}



