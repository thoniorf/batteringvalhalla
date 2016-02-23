package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OnlineMenu extends JPanel {

	private int width = 1024;
	private int height = 768;
	private GameFrame frame;
	private GridBagConstraints constraints;
	private JButtonCustom joinRoom;
	private JButtonCustom newRoom;
	private JTextField ipRoom;
	private JLabel header;
	private JComboBox<String> comboBox;
	private JButtonRound exit;
	private File f;
	public OnlineMenu() {
		super(new GridBagLayout());
		this.frame = GameFrame.instance();
		
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width,
				height);
		setOpaque(false);
		f=new File("Maps");
		if(!f.exists()){
			f.mkdir();
		}
		constraints = new GridBagConstraints();
		joinRoom = new JButtonCustom(ResourcesLoader.mainmenu_images.get(0),
				ResourcesLoader.mainmenu_images.get(1),
				ResourcesLoader.mainmenu_images.get(2));
		newRoom = new JButtonCustom(ResourcesLoader.mainmenu_images.get(0),
				ResourcesLoader.mainmenu_images.get(1),
				ResourcesLoader.mainmenu_images.get(2));
		comboBox=new JComboBox<String>(f.list());
		
		ipRoom=new JTextField();
		ipRoom.setText("IP Room");
		exit=new JButtonRound(ResourcesLoader.mainmenu_images.get(7),
				ResourcesLoader.mainmenu_images.get(8));
		
		
		header = new JLabel("Editor Valhalla");
		header.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 144));
		
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 6;
		constraints.gridheight = 1;
		add(header, constraints);
		
	
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.left=100;
		constraints.fill=2;
		
		add(comboBox, constraints);
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets.right=100;
		add(newRoom, constraints);
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		//constraints.insets.left=100;
		add(ipRoom, constraints);
		
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(joinRoom, constraints);
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		
		add(exit, constraints);
		
		listenerLoeder();
		setVisible(true);
	
		
	}
	
	private void listenerLoeder() {
		exit.addActionListener(e->{
			Chiudere();
			
		});
		newRoom.addActionListener(e->{
		
		});
	repaint();
	}
	public void Chiudere() {

		frame.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(true);
		frame.getLayeredPane().remove(frame.getLayeredPane().getComponentsInLayer(2)[0]);
	}
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		joinRoom.setEnabled(enabled);
		newRoom.setEnabled(enabled);
		exit.setEnabled(enabled);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.optionmenu_images.get(4), 0, 0,width,height, null);
	}

}
