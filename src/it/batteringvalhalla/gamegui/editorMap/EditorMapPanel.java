package it.batteringvalhalla.gamegui.editorMap;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;




















import javafx.scene.chart.PieChart.Data;







import javafx.scene.control.ComboBox;









import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;




















import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.wall.*;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;


public class EditorMapPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private JButtonCustom sfondo1;
	private Image imageSfondo1;
	private Image imageSfondo2;
	private JButtonCustom sfondo2;
	private JButtonCustom muro0;
	private JButtonCustom muro1;
	private JComboBox<String> maps;
	private JButtonCustom carica;
	private Integer state;
	private Integer attrito;
	private List<VerySquareWall> wall;
	private static final int WIDTHSFONDO=1174;
	private static int HEIGHTSFONDO=720;
	private JButtonRound exit;
	private int height=50;
	private int width=50;
	private String nomeMap;
	
	private Image imageCubo0;
	private Image imageCubo1;
	private  Rectangle rectangle;
	private Image sfondoSelezionato;
	private int distanzaLeft ;
	private static final double scartoAltezza=0.87; 
	private static final int widthmappa=824 ;
	private static final int  heightMappa=658 ;
	 
	private JButtonRound save;
	private JLabel header;
	private VerySquareWall nuovoMuro;
	private Arena a;
	private List<Rectangle> spawns;
	
	
	public EditorMapPanel() {
		super();
		setLayout(null);
		this.frame = GameFrame.instance();
		wall=new ArrayList<VerySquareWall>();
		state=0;
		attrito=2;
		distanzaLeft = 40;
		nomeMap=new String();
		
		
		
		header = new JLabel("EditorMap Valhalla");
		header.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 40));
		rectangle=new Rectangle(300, 30, widthmappa, heightMappa);
		imageSfondo1=ResourcesLoader.mainmenu_images.get(9).getScaledInstance(widthmappa, heightMappa,0);
		
		sfondo1=new JButtonCustom(imageSfondo1.getScaledInstance(width , height, 0),imageSfondo1.getScaledInstance(width, height, 0),imageSfondo1.getScaledInstance(width, height, 0));
		
		sfondoSelezionato=imageSfondo1.getScaledInstance(widthmappa, heightMappa, 0);
		a=new Arena(sfondoSelezionato);
		imageSfondo2=ResourcesLoader.mainmenu_images.get(10);
		sfondo2=new JButtonCustom(imageSfondo2,imageSfondo2,imageSfondo2);
		
		imageCubo0=new VerySquareWall(1, 1, -1).getImage();
		muro0=new JButtonCustom(imageCubo0, imageCubo0, imageCubo0);
		imageCubo1=new VerySquareWall(0, 0, 1).getImage();
		
		muro1=new JButtonCustom(imageCubo1, imageCubo1, imageCubo1);
		exit=new JButtonRound(ResourcesLoader.mainmenu_images.get(7).getScaledInstance(width, height, 0),
				ResourcesLoader.mainmenu_images.get(8).getScaledInstance(width, height, 0));
		save= new JButtonRound(ResourcesLoader.exitmenu_images.get(5).getScaledInstance(width, height, 0),
				ResourcesLoader.exitmenu_images.get(6).getScaledInstance(width, height, 0));
		
		spawns=new ArrayList<Rectangle>();
		for(int i=0;i<4;i++){
			spawns.add(new Rectangle(a.getSpawn().get(i).x, a.getSpawn().get(i).y, 80, 80));
		}
		
		maps=new JComboBox<String>(ManagerFilePlayer.loadNameOfMaps());
		maps.addItem("new");
		maps.setSelectedItem("new");
		nomeMap=(String) maps.getSelectedItem();
		carica=new JButtonCustom(ResourcesLoader.mainmenu_images.get(0).getScaledInstance(width, height, 0),
				ResourcesLoader.mainmenu_images.get(1).getScaledInstance(width, height, 0),
				ResourcesLoader.mainmenu_images.get(2).getScaledInstance(width, height, 0));
		
		add(exit);
		add(save);
		add(sfondo1);
		add(sfondo2);
		add(muro0);
		add(muro1);
		add(header);
		add(maps);
		add(carica);
		setBounds(CenterComp.centerX(WIDTHSFONDO), CenterComp.centerY(HEIGHTSFONDO), WIDTHSFONDO, HEIGHTSFONDO);
		setOpaque(false);
		 
		sfondo1.setBounds(distanzaLeft, 80,width,height);
		
		sfondo2.setBounds(distanzaLeft, 160,width,height);
		muro0.setBounds(distanzaLeft, 220,width,height);
		muro1.setBounds(distanzaLeft, 280, width, height);
		
		maps.setBounds(distanzaLeft, 360,120, 30);
		carica.setBounds(distanzaLeft, 400,width+20, height);
		exit.setBounds(distanzaLeft+50, 600, width, height);
		save.setBounds(distanzaLeft-10, 600, width, height);
		
		header.setBounds(distanzaLeft,30,800,50);
		setVisible(true);
		repaint();
		listenerLoader();
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			if(rectangle.contains(e.getX(), e.getY()) && state!= 0){
				if(state==-1)
					nuovoMuro=new VerySquareWall(e.getX(), e.getY(), -1);
				if(state==2)
						nuovoMuro=new VerySquareWall(e.getX(), e.getY(), 2);
				
				if(  canAdd()){
					wall.add(nuovoMuro);
					repaint();}
			
				}
			}
			
			

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		}
@Override
protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	
	g.drawImage(ResourcesLoader.optionmenu_images.get(4), 0,0,WIDTHSFONDO,HEIGHTSFONDO, null);
	g.drawImage(sfondoSelezionato, (int)rectangle.getX(),(int)rectangle.getY(),(int)rectangle.getWidth(),(int)rectangle.getHeight(),null);
	for(int i=0;i<wall.size();i++){
		wall.get(i).paint(g);
	}
	}
@Override
public void setVisible(boolean aFlag) {

	super.setVisible(aFlag);
	exit.setVisible(aFlag);
	sfondo1.setVisible(aFlag);
	sfondo2.setVisible(aFlag);
	muro0.setVisible(aFlag);
	muro1.setVisible(aFlag);
	maps.setVisible(aFlag);
	carica.setVisible(aFlag);
	save.setVisible(aFlag);
}
public void showExitConfirm() {

	System.exit(0);

}
private boolean canAdd() {
	for(int i=0;i<wall.size();i++){
		if(wall.get(i).getRectangle().intersects(nuovoMuro.getRectangle()))
		return false;}
	for(int i=0;i<spawns.size();i++){
		if(spawns.get(i).intersects(nuovoMuro.getRectangle()))
			return false;
	}
	if(!rectangle.contains(nuovoMuro.getRectangle()))
		return false;
		
	return true;}

private void listenerLoader(){
	muro0.addActionListener(e->{
		state=-1;
		
		repaint();
	});;
	
	muro1.addActionListener(e->{
		state=2;
	repaint();
});
	
	sfondo1.addActionListener(e->{
	attrito=2;
	sfondoSelezionato=imageSfondo1;
	repaint();
});
	sfondo2.addActionListener(e->{
		attrito=1;
		sfondoSelezionato=imageSfondo2;
		
		repaint();
	});
	exit.addActionListener(e->{
		
		Chiudere();
	
	});
	save.addActionListener(e->{
			for(int i=0;i<wall.size();i++){
				wall.get(i).setY(wall.get(i).getOrigin().y-rectangle.getY());
				wall.get(i).setX(wall.get(i).getOrigin().x-rectangle.getX());
				}
			
			ManagerFilePlayer.saveMap(attrito,wall,nomeMap);
			Chiudere();
	});
	
	carica.addActionListener(e->{
		
		if(!maps.getSelectedItem().equals("new")){
			wall.removeAll(wall);
			
		if(ManagerFilePlayer.getAttritoMap((String)maps.getSelectedItem())==2){
			attrito=2;
			sfondoSelezionato=imageSfondo1;
		}
		else{
			attrito=1;
			sfondoSelezionato=imageSfondo2;
		}
		wall.addAll(ManagerFilePlayer.getWallsInTheMap((String) maps.getSelectedItem()));
		
		for(int i=0;i<wall.size();i++){
			wall.get(i).setY(wall.get(i).getOrigin().y+rectangle.getY());
			wall.get(i).setX(wall.get(i).getOrigin().x+rectangle.getX());
			}
		}
		nomeMap=(String) maps.getSelectedItem();
		repaint();
	});
	
}


public void Chiudere() {

	frame.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(true);
	frame.getLayeredPane().remove(frame.getLayeredPane().getComponentsInLayer(2)[0]);
	
}
}
