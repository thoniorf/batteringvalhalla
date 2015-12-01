package it.batteringvalhalla.gamegui.menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.actor.Player;
import it.batteringvalhalla.gamecore.sqlite.ScoreFetch;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class ScoreBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints;
	private GameFrame frame;
	private ManagerFilePlayer mfp;
	private JButtonRound restart;
	private JButtonRound exit;
	private JLabel score_header;

	ArrayList<String> scores;
	ArrayList<JLabel> labels;

	private int width = 768;
	private int height = 640;

	public ScoreBoard() {
		super(new GridBagLayout());
		this.frame = GameFrame.instance();
		setBounds(CenterComp.centerX(width), CenterComp.centerY(height), width, height);
		setOpaque(false);
		constraints = new GridBagConstraints();
		labels = new ArrayList<JLabel>();
		loadScores();

		restart = new JButtonRound(ResourcesLoader.scoreboard_images.get(0), ResourcesLoader.scoreboard_images.get(1));
		exit = new JButtonRound(ResourcesLoader.exitmenu_images.get(7), ResourcesLoader.exitmenu_images.get(8));
		score_header = new JLabel("Scoreboard");
		score_header.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 72));

		constraints.weightx = 0.5;
		constraints.weighty = 0.5;

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 10;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(25, 0, 0, 0);
		add(score_header, constraints);

		constraints.insets = new Insets(0, 0, 0, 0);
		for (int i = 0; i < labels.size(); i += 2) {
			constraints.gridx = 0;
			constraints.gridy = i + 1;
			constraints.gridwidth = 4;
			add(labels.get(i), constraints);

			constraints.gridx = 6;
			constraints.gridy = i + 1;
			constraints.gridwidth = 2;
			add(labels.get(i + 1), constraints);
		}

		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0, 25, 8, 0);
		add(restart, constraints);

		constraints.gridx = 6;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0, 0, 8, 35);
		add(exit, constraints);

		setVisible(true);

		listenerLoader();

	}

	private void loadScores() {
		JLabel jAppoggio;
		scores = new ArrayList<String>();
		ScoreFetch scorefetch = new ScoreFetch();
		scorefetch.insertScore(Player.score, Player.username);
		scorefetch.execQuery("Select * from scores order by match desc limit 8;", scores);
		for (String score : scores) {
			jAppoggio = new JLabel(score);
			jAppoggio.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 36));
			labels.add(jAppoggio);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawImage(ResourcesLoader.optionmenu_images.get(4), 0, 0, null);
	}

	private void listenerLoader() {
		restart.addActionListener(e -> {
			frame.startGame();
		});
		exit.addActionListener(e -> {
			frame.restart();
		});

	}

}
