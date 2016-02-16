package it.batteringvalhalla.gamegui.menu;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
	public OnlineMenu() {
		super(new GridBagLayout());
		this.frame = GameFrame.instance();
	
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width,
				height);
		setOpaque(false);
		constraints = new GridBagConstraints();
		joinRoom = new JButtonCustom(ResourcesLoader.mainmenu_images.get(0),
				ResourcesLoader.mainmenu_images.get(1),
				ResourcesLoader.mainmenu_images.get(2));
		newRoom = new JButtonCustom(ResourcesLoader.mainmenu_images.get(0),
				ResourcesLoader.mainmenu_images.get(1),
				ResourcesLoader.mainmenu_images.get(2));
		ipRoom=new JTextField();
		ipRoom.setText("IP Room");
		
		header = new JLabel("Editor Valhalla");
		header.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 144));
		
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(header, constraints);
		
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.left=100;
		constraints.fill=2;
		
		add(ipRoom, constraints);
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 4;
		constraints.gridheight = 2;
		constraints.insets.right=250;
		add(joinRoom, constraints);
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 4;
		constraints.gridheight = 2;
		add(newRoom, constraints);
		
		
		
		setVisible(true);
		
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		joinRoom.setEnabled(enabled);
		newRoom.setEnabled(enabled);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.optionmenu_images.get(4), 0, 0,width,height, null);
	}

}
