package it.batteringvalhalla.gamegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.batteringvalhalla.gamecore.GameManager;
import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.State;
import it.batteringvalhalla.gamecore.input.PlayerControls;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;

public class GamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButtonRound pause;
    private String winner;
    private JLabel username;
    private JLabel scores;
    private JLabel charge;

    public GamePanel() {
	super(null);
	this.setOpaque(false);
	this.setFocusable(true);
	this.addKeyListener(new PlayerControls());
	this.setBounds(0, 0, GameFrame.size.width, GameFrame.size.height);
	requestFocusInWindow();
	// initialization
	username = new JLabel();
	username.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 36));
	username.setText(Player.getUsername());

	scores = new JLabel();
	scores.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 36));
	scores.setAlignmentY(JLabel.CENTER_ALIGNMENT);
	scores.setText(GameManager.getRound().toString());

	charge = new JLabel();
	charge.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 36));
	charge.setText("Charge: Ready");

	pause = new JButtonRound(ResourcesLoader.images.get("pause"), ResourcesLoader.images.get("pause_hover"));
	// setting bounds
	pause.setLocation(10, 10);
	username.setBounds(CenterComp.centerX(ResourcesLoader.images.get("game_header").getWidth(null)) + 24, 24, 268,
		38);
	scores.setBounds(username.getX() + username.getWidth() + 24, 24, 58, 38);
	charge.setBounds(scores.getX() + scores.getWidth() + 24, 24, 324, 38);
	// adding
	add(pause);
	add(username);
	add(scores);
	add(charge);
	setVisible(true);
	listenerLoader();
	winner = "";
    }

    public void gameOver() {
	scores.setText("GAME OVER");
	scores.setBounds(username.getX() + username.getWidth() + 24, 24, 400, 38);
	charge.setVisible(false);
    }

    private void listenerLoader() {
	pause.addActionListener(e -> {
	    if (State.Pause.equals(GameManager.getState())) {
		GameManager.setState(State.Run);
		updateUI();
	    } else {
		GameManager.setState(State.Pause);
		GameFrame.instance().showPause();
	    }
	});
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	// paint World
	GameWorld.paint(g2d);
	g.drawImage(ResourcesLoader.images.get("game_header"),
		CenterComp.centerX(ResourcesLoader.images.get("game_header").getWidth(null)), 10, null);
	if (winner != "") {
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 56));
	    g.setColor(Color.BLACK);
	    g.drawImage(ResourcesLoader.images.get("winner_header"),
		    CenterComp.centerX(ResourcesLoader.images.get("winner_header").getWidth(null)),
		    CenterComp.centerY(ResourcesLoader.images.get("winner_header").getHeight(null)), null);

	    java.awt.FontMetrics metrics = g.getFontMetrics();
	    int x = (GameFrame.size.width - metrics.stringWidth(winner)) / 2;
	    int y = ((GameFrame.size.height - metrics.getHeight()) / 2) + metrics.getAscent();

	    g.drawString(winner, x, y - metrics.getHeight() / 2);
	    g.drawString("Win!", x, y + metrics.getHeight() / 2);
	}
    }

    public void setCharge(Boolean status) {
	if (status) {
	    charge.setText("Charge: Ready");
	} else {
	    charge.setText("Charge: Not Ready");
	}
    }

    public void setScore(String s) {
	scores.setText(s);
    }

    public void winner(String winner) {
	this.winner = winner;
    }

}
