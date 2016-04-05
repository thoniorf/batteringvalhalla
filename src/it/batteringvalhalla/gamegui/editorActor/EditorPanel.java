package it.batteringvalhalla.gamegui.editorActor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;







import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class EditorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int HEIGHTSFONDO = 690;
	private static final int WIDTHSFONDO = 1024;

	private static final int HEIGHTESTA = 100;
	private static final int WIDTHTESTA = 100;
	private static final int HEIGHTBUSTO = 100;
	private static final int WIDTHBUSTO = 100;
	private static final int HEIGHTCAPRA = 100;
	private static final int WIDTHCAPRA = 100;

	
	private static final int yTesta = 250;
	private static final int yBusto = 300;
	private static final int yCapra = 305;

	private static final int xImage = 480;


//button"riga""lato"//s"inistra",d"estra"
	private JButtonRound button1s;
	private JButtonRound button1d;
	private JButtonRound button2s;
	private JButtonRound button2d;
	private JButtonRound button3s;
	private JButtonRound button3d;
	private JLabel header;
	
	private JButtonRound exit;

	private ImageEditor ie;
	private GameFrame frame;
	private GridBagConstraints constraints ;
	
	Font font;

	public EditorPanel() {
		super(new GridBagLayout());
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(WIDTHSFONDO), CenterComp.centerY(HEIGHTSFONDO), WIDTHSFONDO,
				HEIGHTSFONDO);
		setOpaque(false);
		
		
		
		ie = new ImageEditor();
		
		setBackground(Color.PINK);
		constraints = new GridBagConstraints();
		int sizeButtom=80;
		Image frecciaSinistra1=ResourcesLoader.leftArrow.get(0).getScaledInstance(sizeButtom,sizeButtom, 0);
		Image frecciaSinistra2=ResourcesLoader.leftArrow.get(1).getScaledInstance(sizeButtom,sizeButtom, 0);
		Image frecciaDestra1=ResourcesLoader.rightArrow.get(0).getScaledInstance(sizeButtom,sizeButtom, 0);
		Image frecciaDestra2=ResourcesLoader.rightArrow.get(1).getScaledInstance(sizeButtom,sizeButtom, 0);
		
		button1s=new JButtonRound(frecciaSinistra1, frecciaSinistra2);
		button1d=new JButtonRound(frecciaDestra1, frecciaDestra2);
		button2s=new JButtonRound(frecciaSinistra1, frecciaSinistra2);
		button2d=new JButtonRound(frecciaDestra1, frecciaDestra2);
		button3s=new JButtonRound(frecciaSinistra1, frecciaSinistra2);
		button3d=new JButtonRound(frecciaDestra1, frecciaDestra2);
		exit=new JButtonRound(ResourcesLoader.images.get("exit"),
				ResourcesLoader.images.get("exit_hover"));
		
		header = new JLabel("Editor Valhalla");
		header.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 144));
	
		
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.insets.top=30;
		add(header, constraints);
		
		constraints.insets.top=0;
		
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 4;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		
		add(button1d, constraints);

		constraints.weighty = 0.5;
		constraints.weightx = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(button1s, constraints);

		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		
		add(button2d, constraints);

		constraints.weighty = 0.5;
		constraints.weightx = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(button2s, constraints);

		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 4;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(button3d, constraints);

		constraints.weighty = 0.5;
		constraints.weightx = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(button3s, constraints);

		
		
		constraints.weighty = 0.5;
		constraints.weightx = 0.5;
		constraints.gridx = 3;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.bottom=30;
		add(exit, constraints);
		
		

		setVisible(true);
		listenerLoader();

		repaint();
		

			

	}

	

	


	
	private void save(){
		ManagerFilePlayer.setTop(ImageEditor.getIndexTesta());
		ManagerFilePlayer.setMid(ImageEditor.getIndexBusto());
		ManagerFilePlayer.setBot(ImageEditor.getIndexCapra());
		ManagerFilePlayer.save();
	}
	
	private void listenerLoader(){
		button1d.addActionListener(e->{
			ie.spostaHead(1);
			repaint();
		});
		button1s.addActionListener(e->{
			ie.spostaHead(-1);
			repaint();
		});
		button2d.addActionListener(e->{
			ie.spostaBust(1);
			repaint();
		});
		button2s.addActionListener(e->{
			ie.spostaBust(-1);
			repaint();
		});
		button3d.addActionListener(e->{
			ie.spostaGoat(1);
			repaint();
		});
		button3s.addActionListener(e->{
			ie.spostaGoat(-1);
			repaint();
		});
		
		exit.addActionListener(e->{
			save();
			Chiudere();
		
		});
	}
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		button1d.setEnabled(enabled);
		button1s.setEnabled(enabled);
		button2d.setEnabled(enabled);
		button2s.setEnabled(enabled);
		button3d.setEnabled(enabled);
		button3s.setEnabled(enabled);
		exit.setEnabled(enabled);
	
	}
	@Override
	protected void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(ResourcesLoader.optionmenu_images.get(4), 0,0,WIDTHSFONDO,HEIGHTSFONDO, null);
		
		

		g2d.drawImage(ie.getImageGoat(), xImage,yCapra,(int) (WIDTHCAPRA*1.5),(int) (HEIGHTCAPRA*1.5), null);
		g2d.drawImage(ie.getImageHead(), xImage,yTesta,(int) (WIDTHTESTA*1.5), (int)(HEIGHTESTA*1.5), null);
		
		g2d.drawImage(ie.getImageBust(), xImage, yBusto-10, (int)(WIDTHBUSTO*1.5),(int) (HEIGHTBUSTO*1.5), null);
		g2d.drawImage(ie.getImageBust1(), xImage, yBusto+10, (int)(WIDTHBUSTO*1.5),(int) (HEIGHTBUSTO*1.5), null);

		g.setColor(Color.BLACK);

	
	}

	public void Chiudere() {

		frame.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(true);
		frame.getLayeredPane().remove(frame.getLayeredPane().getComponentsInLayer(2)[0]);
	}

	public void showExitConfirm() {

		System.exit(0);

	}
}
