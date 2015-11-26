package it.batteringvalhalla.gamegui.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class ExitMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints;
	private GameFrame frame;
	private ManagerFilePlayer mfp;
	private JButtonRound no;
	private JButtonRound yes;
	private JLabel text;

	private int width = 512;
	private int height = 256;

	public ExitMenu() {
		super(new GridBagLayout());
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
		setBackground(Color.RED);
		constraints = new GridBagConstraints();
		yes = new JButtonRound(ResourcesLoader.exitmenu_images.get(1), ResourcesLoader.exitmenu_images.get(3));
		no = new JButtonRound(ResourcesLoader.exitmenu_images.get(2), ResourcesLoader.exitmenu_images.get(4));
		text = new JLabel("Are you sure ?");
		text.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 72));

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.insets = new Insets(30, 0, 0, 0);
		add(text, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 8, 0);
		add(yes, constraints);
		constraints.gridx = 2;
		constraints.gridwidth = 2;
		add(no, constraints);

		setVisible(true);
		listenerLoader();
	}

	private void listenerLoader() {
		yes.addActionListener(e -> {
			System.exit(0);
		});
		no.addActionListener(e -> {
			frame.getLayeredPane().getComponentsInLayer(0)[0].setEnabled(true);
			frame.getLayeredPane().remove(frame.getLayeredPane().getComponentsInLayer(2)[0]);
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.exitmenu_images.get(0), 0, 0, null);
	}

}
