package it.batteringvalhalla.gamegui.menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class UsernameMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints;

	private GameFrame frame;
	private JButtonRound no;
	private JButtonRound yes;
	private JTextField userfield;

	private int width = 512;
	private int height = 256;

	public UsernameMenu() {
		super(new GridBagLayout());
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
		setOpaque(false);
		constraints = new GridBagConstraints();
		yes = new JButtonRound(ResourcesLoader.exitmenu_images.get(5), ResourcesLoader.exitmenu_images.get(6));
		no = new JButtonRound(ResourcesLoader.exitmenu_images.get(7), ResourcesLoader.exitmenu_images.get(8));
		userfield = new JTextField();

		userfield.setBorder(null);
		userfield.setOpaque(false);
		userfield.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 72));
		userfield.setHorizontalAlignment(JTextField.CENTER);
		userfield.setDocument(new PlainDocument() {
			private static final long serialVersionUID = 1L;

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str == null) {
					return;
				}

				if ((getLength() + str.length()) <= 8) {
					super.insertString(offs, str, a);
				}

			}
		});

		userfield.setText(ManagerFilePlayer.getName());

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.insets = new Insets(30, 0, 0, 0);
		this.add(userfield, constraints);

		constraints.fill = GridBagConstraints.NONE;
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.exitmenu_images.get(0), 0, 0, null);
	}

	private void listenerLoader() {
		yes.addActionListener(e -> {
			Player.setUsername(userfield.getText());
			ManagerFilePlayer.setName(userfield.getText());
			frame.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(true);
			frame.getLayeredPane().remove(frame.getLayeredPane().getComponentsInLayer(2)[0]);
		});
		no.addActionListener(e -> {
			Player.setUsername(ManagerFilePlayer.getName());
			frame.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(true);
			frame.getLayeredPane().remove(frame.getLayeredPane().getComponentsInLayer(2)[0]);
		});
	}

	public JTextField getUserfield() {
		return userfield;
	}
}
