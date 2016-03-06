package it.batteringvalhalla.gamegui.menu;

import java.awt.Font;
import java.awt.Graphics;

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

	private GameFrame frame;
	private JButtonRound no;
	private JButtonRound yes;
	private JTextField userfield;

	private int width = 512;
	private int height = 256;

	public UsernameMenu() {
		super(null);
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
		setOpaque(false);
		// initialize
		yes = new JButtonRound(ResourcesLoader.images.get("confirm_blue"),
				ResourcesLoader.images.get("confirm_blue_hover"));
		no = new JButtonRound(ResourcesLoader.images.get("exit_red"), ResourcesLoader.images.get("exit_red_hover"));
		userfield = new JTextField();

		userfield.setBorder(null);
		userfield.setOpaque(false);
		userfield.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 54));
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
		// setting bounds
		userfield.setBounds(82, 40, 313, 56);
		yes.setBounds(105, 123, yes.getWidth(), yes.getHeight());
		no.setBounds(274, 123, no.getWidth(), no.getHeight());

		add(userfield);
		add(yes);
		add(no);

		setVisible(true);
		listenerLoader();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.images.get("background_5x2"), 0, 0, null);
		g.drawImage(ResourcesLoader.images.get("userfield_header"), 55, 20, null);
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
