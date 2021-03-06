package it.batteringvalhalla.gamegui.editorMap;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.customcomponents.JCustomComboBox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditorMapPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private JButtonCustom sfondo1;
	private JButtonCustom sfondo2;
	private JButtonCustom sfondo3;

	private Image imageSfondo1;
	private Image imageSfondo2;
	private Image imageSfondo3;

	private JButtonCustom muro0;
	private JButtonCustom muro1;

	private JCustomComboBox maps;
	private List<VerySquareWall> wall;
	private List<Player> players;

	private JButtonCustom carica;
	private JButtonCustom save;
	private JButtonCustom exit;

	private JTextField nomeMappa;
	private Integer state;
	private Integer attrito;

	private static final int WIDTHSFONDO = 1160;
	private static int HEIGHTSFONDO = 768;
	private int height = 50;
	private int width = 50;
	private VerySquareWall tmp;

	private Image imageCubo0;
	private Image imageCubo1;
	private Rectangle rectangle;
	private Image sfondoSelezionato;
	private int distanzaLeft;

	private JLabel header;
	private JLabel textMuri;
	private JLabel textSfondi;
	private JLabel textCaricaMappa;
	private JLabel textNomeMappa;

	private VerySquareWall nuovoMuro;
	private Arena a;
	private int indexSpwan;
	private Point lastPosition;
	private int ySfondo = 130;
	private int yMuri = 240;

	public EditorMapPanel() {
		super();
		setLayout(null);
		setBounds(CenterComp.centerX(WIDTHSFONDO),
				CenterComp.centerY(HEIGHTSFONDO), WIDTHSFONDO, HEIGHTSFONDO);
		this.frame = GameFrame.instance();

		this.frame.setMinimumSize(new Dimension(WIDTHSFONDO, HEIGHTSFONDO));

		wall = new ArrayList<VerySquareWall>();
		state = -2;
		attrito = 500;
		distanzaLeft = 50;

		sfondoSelezionato = ResourcesLoader.mainmenu_images.get(9);
		header = new JLabel("EditorMap Valhalla");
		header.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 35));
		textSfondi = new JLabel("Sfondi:");
		textSfondi.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 25));
		textMuri = new JLabel("Muri:");
		textMuri.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 25));
		textCaricaMappa = new JLabel("Carica Mappa");
		textCaricaMappa.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 25));
		textNomeMappa = new JLabel("Nome Mappa");
		textNomeMappa.setFont(new Font(ResourcesLoader.gothic.getName(),
				ResourcesLoader.gothic.getStyle(), 25));

		rectangle = new Rectangle(300, 30, sfondoSelezionato.getWidth(null),
				sfondoSelezionato.getHeight(null));
		imageSfondo1 = ResourcesLoader.mainmenu_images.get(9);

		sfondo1 = new JButtonCustom(imageSfondo1.getScaledInstance(width,
				height, 0), imageSfondo1.getScaledInstance(width, height, 0),
				imageSfondo1.getScaledInstance(width, height, 0));

		a = new Arena(sfondoSelezionato);
		imageSfondo2 = ResourcesLoader.mainmenu_images.get(11);
		sfondo2 = new JButtonCustom(imageSfondo2.getScaledInstance(width,
				height, 0), imageSfondo2.getScaledInstance(width, height, 0),
				imageSfondo2.getScaledInstance(width, height, 0));
		imageSfondo3 = ResourcesLoader.mainmenu_images.get(12);
		sfondo3 = new JButtonCustom(imageSfondo3.getScaledInstance(width,
				height, 0), imageSfondo3.getScaledInstance(width, height, 0),
				imageSfondo3.getScaledInstance(width, height, 0));

		imageCubo0 = new VerySquareWall(1, 1, -1).getImage();
		muro0 = new JButtonCustom(
				imageCubo0.getScaledInstance(width, height, 0),
				imageCubo0.getScaledInstance(width, height, 0),
				imageCubo0.getScaledInstance(width, height, 0));
		imageCubo1 = new VerySquareWall(0, 0, 1).getImage();

		muro1 = new JButtonCustom(
				imageCubo1.getScaledInstance(width, height, 0),
				imageCubo1.getScaledInstance(width, height, 0),
				imageCubo1.getScaledInstance(width, height, 0));
		int delta = 20;
		exit = new JButtonCustom(ResourcesLoader.images.get("exit")
				.getScaledInstance(width + delta, height, 0),
				ResourcesLoader.images.get("exit_hover").getScaledInstance(
						width + delta, height, 0), ResourcesLoader.images.get(
						"exit_selected").getScaledInstance(width + delta,
						height, 0));

		save = new JButtonCustom(ResourcesLoader.images.get("save")
				.getScaledInstance(width + delta, height, 0),
				ResourcesLoader.images.get("save_hover").getScaledInstance(
						width + delta, height, 0), ResourcesLoader.images.get(
						"save_selected").getScaledInstance(width + delta,
						height, 0));
		nomeMappa = new JTextField();
		nomeMappa.setText(newName());
		players = new ArrayList<Player>();
		for (int i = 0; i < 4; i++) {
			players.add(new Player(new Point((a.getSpawn().get(i).x + 50), (a
					.getSpawn().get(i).y))));
			players.get(i).update();
		}

		maps = new JCustomComboBox(ManagerFilePlayer.loadNameOfMaps());

		maps.addItem("new");
		maps.setSelectedItem("new");
		carica = new JButtonCustom(ResourcesLoader.images.get("load")
				.getScaledInstance(width * 2 + delta, height, 0),
				ResourcesLoader.images.get("load_hover").getScaledInstance(
						width * 2 + delta, height, 0), ResourcesLoader.images
						.get("load_selected").getScaledInstance(
								width * 2 + delta, height, 0));

		add(exit);
		add(save);
		add(sfondo1);
		add(sfondo2);
		add(sfondo3);
		add(muro0);
		add(muro1);
		add(header);
		add(textSfondi);
		add(textMuri);
		add(textCaricaMappa);
		add(textNomeMappa);
		add(maps);
		add(carica);
		add(nomeMappa);

		setBounds(CenterComp.centerX(WIDTHSFONDO),
				CenterComp.centerY(HEIGHTSFONDO), WIDTHSFONDO, HEIGHTSFONDO);
		setOpaque(false);

		sfondo1.setBounds(distanzaLeft, ySfondo, sfondo1.getIcon()
				.getIconWidth(), sfondo1.getIcon().getIconHeight());

		sfondo2.setBounds(distanzaLeft + sfondo1.getIcon().getIconWidth() + 15,
				ySfondo, sfondo2.getIcon().getIconWidth(), sfondo2.getIcon()
						.getIconHeight());
		sfondo3.setBounds(distanzaLeft + sfondo1.getIcon().getIconWidth()
				+ sfondo2.getIcon().getIconWidth() + 30, ySfondo, sfondo3
				.getIcon().getIconWidth(), sfondo3.getIcon().getIconHeight());
		muro0.setBounds(distanzaLeft, yMuri, muro0.getIcon().getIconWidth(),
				muro0.getIcon().getIconHeight());
		muro1.setBounds(distanzaLeft * 2 + 15, yMuri, muro1.getIcon()
				.getIconWidth(), muro1.getIcon().getIconHeight());

		maps.setBounds(distanzaLeft, 360, 200, 70);

		carica.setBounds(distanzaLeft, 440, carica.getIcon().getIconWidth(),
				carica.getIcon().getIconHeight());
		nomeMappa.setBounds(distanzaLeft, 550, 150, 30);
		exit.setBounds(distanzaLeft + 50 + delta, 600, exit.getIcon()
				.getIconWidth(), exit.getIcon().getIconHeight());
		save.setBounds(distanzaLeft - 10, 600, save.getIcon().getIconWidth(),
				save.getIcon().getIconHeight());

		header.setBounds(distanzaLeft, 30, 700, 50);
		textSfondi.setBounds(distanzaLeft, ySfondo - 40, 700, 50);
		textMuri.setBounds(distanzaLeft, yMuri - 40, 700, 50);
		textCaricaMappa.setBounds(distanzaLeft, 320, 700, 50);
		textNomeMappa.setBounds(distanzaLeft, 510, 700, 50);
		setVisible(true);
		repaint();
		listenerLoader();
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (rectangle.contains(e.getX(), e.getY())) {
						if (state >= 0) {
							if (state == 0)
								nuovoMuro = new VerySquareWall(e.getX(),
										e.getY(), -1);
							if (state == 2)
								nuovoMuro = new VerySquareWall(e.getX(),
										e.getY(), 2);

							if (canAddWall()) {
								wall.add(nuovoMuro);
								repaint();
							}
						} else if (state == -1) {
							if (canMovePlayer())
								releasedSpawn(e.getPoint());
							else {
								releasedSpawn(lastPosition);
							}
							state = -2;
						}
					}

				}
				if (e.getButton() == MouseEvent.BUTTON3) {

					for (int i = 0; i < wall.size(); i++) {
						if (wall.get(i).getRectangle().contains(e.getPoint())) {
							wall.remove(i);
							repaint();
						}
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (rectangle.contains(e.getX(), e.getY())) {
					pressedSpawn(e.getPoint());
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {

				if (state >= 0) {

					if (!rectangle.contains(e.getX(), e.getY())) {

						if (tmp != null)
							tmp.getOrigin().setLocation(-50, -50);
						else
							tmp.getOrigin().setLocation(e.getX(), e.getY());
					}

					else {
						tmp.getOrigin().setLocation(e.getX(), e.getY());
					}
					tmp.update();
					repaint();
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {

				if (state == -1) {
					if (rectangle.contains(e.getX(), e.getY())) {

						players.get(indexSpwan).getOrigin()
								.move(e.getX(), e.getY());
						players.get(indexSpwan).update();
					}

					repaint();
				}

			}
		});

	}

	private void pressedSpawn(Point p) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getShape().contains(p)) {
				indexSpwan = i;
				state = -1;

				lastPosition = new Point(players.get(i).getOrigin());
			}
		}

	}

	private void releasedSpawn(Point p) {
		players.get(indexSpwan).getOrigin().move(p.x, p.y);
		players.get(indexSpwan).update();
		rimuoviOstacoli(players.get(indexSpwan).getShape());
		repaint();
	}

	private void rimuoviOstacoli(Shape shape) {
		for (int i = 0; i < wall.size(); i++) {
			if (wall.get(i).getRectangle().intersects((Rectangle) shape)) {
				wall.remove(i);
			}
		}

	}

	private String newName() {
		int i = 0;

		while (ManagerFilePlayer.mapExist("new" + i)) {
			i++;
		}

		return "new" + i;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(ResourcesLoader.optionmenu_images.get(4), 0, 0,
				WIDTHSFONDO, HEIGHTSFONDO, null);
		g.drawImage(sfondoSelezionato, (int) rectangle.getX(),
				(int) rectangle.getY(), (int) rectangle.getWidth(),
				(int) rectangle.getHeight(), null);
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < wall.size(); i++) {
			wall.get(i).paint(g2d);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).paint(g2d);

		}
		if (state >= 0)
			tmp.paint(g2d);

	}

	@Override
	public void setVisible(boolean aFlag) {

		super.setVisible(aFlag);
		exit.setVisible(aFlag);
		sfondo1.setVisible(aFlag);
		sfondo2.setVisible(aFlag);
		sfondo3.setVisible(aFlag);
		muro0.setVisible(aFlag);
		muro1.setVisible(aFlag);
		maps.setVisible(aFlag);
		carica.setVisible(aFlag);
		save.setVisible(aFlag);
		nomeMappa.setVisible(aFlag);
	}

	private boolean canAddWall() {
		for (int i = 0; i < wall.size(); i++) {
			if (wall.get(i).getRectangle().intersects(nuovoMuro.getRectangle()))
				return false;
		}

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getShape()
					.intersects((nuovoMuro.getRectangle())))
				return false;
		}
		if (!rectangle.contains(nuovoMuro.getRectangle()))
			return false;

		return true;
	}

	private boolean canMovePlayer() {
		for (int i = 0; i < players.size(); i++) {
			if (players
					.get(i)
					.getShape()
					.intersects(
							players.get(indexSpwan).getShape().getBounds2D())
					&& i != indexSpwan)
				return false;
		}
		return true;
	}

	private void listenerLoader() {
		muro0.addActionListener(e -> {
			state = 0;
			tmp = new VerySquareWall(-50, -50, -1);
			repaint();
		});

		muro1.addActionListener(e -> {
			state = 2;
			tmp = new VerySquareWall(-50, -50, 2);
			repaint();
		});

		sfondo1.addActionListener(e -> {
			attrito = 500;
			sfondoSelezionato = imageSfondo1;
			repaint();
		});
		sfondo2.addActionListener(e -> {
			attrito = 250;
			sfondoSelezionato = imageSfondo2;

			repaint();
		});

		sfondo3.addActionListener(e -> {
			attrito = 0;
			sfondoSelezionato = imageSfondo3;

			repaint();
		});

		exit.addActionListener(e -> {

			Chiudere();

		});
		save.addActionListener(e -> {
			if (!nomeMappa.getText().equals("new")) {

				ManagerFilePlayer.saveMap(attrito, wall, players,
						nomeMappa.getText(), rectangle.x, rectangle.y);
				Chiudere();
			}
		});

		carica.addActionListener(e -> {
			wall.removeAll(wall);
			if (!maps.getSelectedItem().equals("new")) {

				players.removeAll(players);
				if (ManagerFilePlayer.getAttritoMap((String) maps
						.getSelectedItem()) == 2) {
					attrito = 500;
					sfondoSelezionato = imageSfondo1;
				} else if (ManagerFilePlayer.getAttritoMap((String) maps
						.getSelectedItem()) == 1) {
					attrito = 250;
					sfondoSelezionato = imageSfondo2;
				} else {
					attrito = 0;
					sfondoSelezionato = imageSfondo3;

				}

				wall.addAll(ManagerFilePlayer.getWallsInTheMap(
						(String) maps.getSelectedItem(), rectangle.x,
						rectangle.y));

				for (int i = 0; i < wall.size(); i++) {
					wall.get(i).update();

				}

				players.addAll(ManagerFilePlayer.getSpawnInTheMap(
						(String) maps.getSelectedItem(), rectangle.x,
						rectangle.y));
				for (int i = 0; i < players.size(); i++) {
					players.get(i).update();
				}

				nomeMappa.setText((String) maps.getSelectedItem());

			}

			repaint();
		});
	}

	public void Chiudere() {
		frame.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(true);
		frame.getLayeredPane().remove(
				frame.getLayeredPane().getComponentsInLayer(2)[0]);
		frame.showMenu();

	}
}
