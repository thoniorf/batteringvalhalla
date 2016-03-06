package it.batteringvalhalla.gamegui.menu;

import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private GameFrame frame;
	private GridBagConstraints constraints;
	private JButtonCustom play;
	private JButtonCustom online;
	private JButtonCustom editor;
	private JButtonCustom options;
	private JButtonCustom exit;

	private int width = 1024;
	private int height = 768;

	public MainMenu() {
		super(null);
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
		setOpaque(false);
		play = new JButtonCustom(ResourcesLoader.images.get("play"), ResourcesLoader.images.get("play_hover"),
				ResourcesLoader.images.get("play_selected"));
		online = new JButtonCustom(ResourcesLoader.images.get("online"), ResourcesLoader.images.get("online_hover"),
				ResourcesLoader.images.get("online_selected"));
		editor = new JButtonCustom(ResourcesLoader.images.get("editor"), ResourcesLoader.images.get("editor_hover"),
				ResourcesLoader.images.get("editor_selected"));
		options = new JButtonCustom(ResourcesLoader.images.get("options"), ResourcesLoader.images.get("options_hover"),
				ResourcesLoader.images.get("options_selected"));
		exit = new JButtonCustom(ResourcesLoader.images.get("exit"), ResourcesLoader.images.get("exit_hover"),
				ResourcesLoader.images.get("exit_selected"));

		play.setBounds(CenterComp.centerX(play.getWidth()) - play.getWidth() / 2,
				ResourcesLoader.header.getHeight(null) + 50, play.getWidth(), play.getHeight());
		add(play);
		online.setBounds(CenterComp.centerX(online.getWidth()) - online.getWidth() / 2,
				play.getY() + play.getHeight() + 20, online.getWidth(), online.getHeight());
		add(online);
		editor.setBounds(CenterComp.centerX(editor.getWidth()) - editor.getWidth() / 2,
				online.getY() + online.getHeight() + 20, editor.getWidth(), editor.getHeight());
		add(editor);
		options.setBounds(CenterComp.centerX(options.getWidth()) - options.getWidth() / 2,
				editor.getY() + editor.getHeight() + 20, options.getWidth(), options.getHeight());
		add(options);
		exit.setBounds(CenterComp.centerX(exit.getWidth()) + 180, options.getY() + options.getHeight(), exit.getWidth(),
				exit.getHeight());
		add(exit);

		revalidate();
		setVisible(true);
		listenerLoader();
	}

	private void listenerLoader() {
		play.addActionListener(e -> {
			setEnabled(false);
			frame.showArcadeEditor();
		});
		online.addActionListener(e -> {
			setEnabled(false);
			frame.showOnline();
		});
		editor.addActionListener(e -> {
			setEnabled(false);
			frame.showEditorsMenu();
		});
		options.addActionListener(e -> {
			setEnabled(false);
			frame.showOptions();
		});
		exit.addActionListener(e -> {
			setEnabled(false);
			frame.showExit();
		});

	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		play.setEnabled(enabled);
		online.setEnabled(enabled);
		options.setEnabled(enabled);
		editor.setEnabled(enabled);
		exit.setEnabled(enabled);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourcesLoader.header, 10, 10, null);
	}

}
