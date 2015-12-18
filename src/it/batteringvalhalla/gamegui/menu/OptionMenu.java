package it.batteringvalhalla.gamegui.menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;
import it.batteringvalhalla.gamegui.sound.Sound;

public class OptionMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints;
	private GameFrame frame;

	// Sound graphics objects
	private JButtonRound sound_on;
	private JButtonRound sound_off;

	// Panel graphics objects
	private JButtonRound back;

	private int width = 768;
	private int height = 640;

	public OptionMenu() {
		super(new GridBagLayout());
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
		setOpaque(false);
		constraints = new GridBagConstraints();
		sound_on = new JButtonRound(ResourcesLoader.optionmenu_images.get(0), ResourcesLoader.optionmenu_images.get(1));
		sound_off = new JButtonRound(ResourcesLoader.optionmenu_images.get(2),
				ResourcesLoader.optionmenu_images.get(3));
		if (ManagerFilePlayer.soundOn()) {
			sound_on.setSelected(true);
			sound_off.setSelected(false);

		} else {
			sound_on.setSelected(false);
			sound_off.setSelected(true);

		}
		JLabel sound_header = new JLabel("Sounds:");
		sound_header.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 72));
		JLabel controls_header = new JLabel("Controls");
		controls_header.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 72));
		JLabel keys_label = new JLabel(new ImageIcon(ResourcesLoader.optionmenu_images.get(7)));
		back = new JButtonRound(ResourcesLoader.optionmenu_images.get(5), ResourcesLoader.optionmenu_images.get(6));

		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.insets = new Insets(25, 25, 0, 0);
		add(sound_header, constraints);

		constraints.weightx = 0.4;
		constraints.gridx = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(sound_on, constraints);

		constraints.gridx = 5;
		add(sound_off, constraints);

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 6;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		add(controls_header, constraints);

		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 6;
		constraints.gridheight = 1;
		constraints.insets = new Insets(35, 0, 0, 0);
		add(keys_label, constraints);

		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0, 35, 35, 0);
		add(back, constraints);

		listenerLoader();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.optionmenu_images.get(4), 0, 0, null);
	}

	private void listenerLoader() {
		sound_on.addActionListener(e -> {
			sound_on.setSelected(true);
			sound_off.setSelected(false);
			ManagerFilePlayer.setSound("1");
			ManagerFilePlayer.save();
			Sound.menu.play();
		});
		sound_off.addActionListener(e -> {
			sound_off.setSelected(true);
			sound_on.setSelected(false);
			ManagerFilePlayer.setSound("0");
			ManagerFilePlayer.save();
			Sound.menu.stop();
		});
		back.addActionListener(e -> {
			frame.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(true);
			frame.getLayeredPane().remove(frame.getLayeredPane().getComponentsInLayer(2)[0]);
		});
	}
}
