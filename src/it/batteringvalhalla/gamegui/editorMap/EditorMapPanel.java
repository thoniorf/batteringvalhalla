package it.batteringvalhalla.gamegui.editorMap;

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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;
import it.batteringvalhalla.gamegui.CenterComp;
import it.batteringvalhalla.gamegui.GameFrame;
import it.batteringvalhalla.gamegui.menu.button.JButtonCustom;
import it.batteringvalhalla.gamegui.menu.button.JButtonRound;
import it.batteringvalhalla.gamegui.menu.customcomponents.JCustomComboBox;

public class EditorMapPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private JButtonCustom sfondo1;
	private Image imageSfondo1;
	private Image imageSfondo2;
	private JButtonCustom sfondo2;
	private JButtonCustom muro0;
	private JButtonCustom muro1;
	private JCustomComboBox maps;
	private JButtonCustom carica;
	private JTextField nomeMappa;
	private Integer state;
	private Integer attrito;
	private List<VerySquareWall> wall;
	private static final int WIDTHSFONDO = 1174;
	private static int HEIGHTSFONDO = 720;
	private JButtonRound exit;
	private int height = 50;
	private int width = 50;
	private VerySquareWall tmp;

	private Image imageCubo0;
	private Image imageCubo1;
	private Rectangle rectangle;
	private Image sfondoSelezionato;
	private int distanzaLeft;
	


	private JButtonRound save;
	private JLabel header;
	private VerySquareWall nuovoMuro;
	private Arena a;
	
	private List<Player> players;
	private int indexSpwan;

	public EditorMapPanel() {
		super();
		setLayout(null);
		this.frame = GameFrame.instance();
		wall = new ArrayList<VerySquareWall>();
		state = -2;
		attrito = 2;
		distanzaLeft = 50;
		
		sfondoSelezionato = ResourcesLoader.mainmenu_images.get(9);
		header = new JLabel("EditorMap Valhalla");
		header.setFont(new Font(ResourcesLoader.gothic.getName(), ResourcesLoader.gothic.getStyle(), 30));
		rectangle = new Rectangle(300, 30, sfondoSelezionato.getWidth(null), sfondoSelezionato.getHeight(null));
		imageSfondo1 = ResourcesLoader.mainmenu_images.get(9);

		sfondo1 = new JButtonCustom(imageSfondo1.getScaledInstance(width, height, 0),
				imageSfondo1.getScaledInstance(width, height, 0), imageSfondo1.getScaledInstance(width, height, 0));

		
		a = new Arena(sfondoSelezionato);
		imageSfondo2 = ResourcesLoader.mainmenu_images.get(10);
		sfondo2 = new JButtonCustom(imageSfondo2, imageSfondo2, imageSfondo2);
		
		imageCubo0 = new VerySquareWall(1, 1, -1).getImage();
		muro0 = new JButtonCustom(imageCubo0, imageCubo0, imageCubo0);
		imageCubo1 = new VerySquareWall(0, 0, 1).getImage();

		muro1 = new JButtonCustom(imageCubo1, imageCubo1, imageCubo1);
		exit = new JButtonRound(ResourcesLoader.images.get("exit").getScaledInstance(width, height, 0),
				ResourcesLoader.images.get("exit_hover").getScaledInstance(width, height, 0));
		save = new JButtonRound(ResourcesLoader.exitmenu_images.get(5).getScaledInstance(width, height, 0),
				ResourcesLoader.exitmenu_images.get(6).getScaledInstance(width, height, 0));
		nomeMappa=new  JTextField();
		nomeMappa.setText(newNome());
		players=new ArrayList<Player>();
		for (int i = 0; i < 4; i++) {
			players.add(new Player(new Point( (int) (a.getSpawn().get(i).x), (int )(a.getSpawn().get(i).y))));
			players.get(i).update();}

		
		maps = new JCustomComboBox(ManagerFilePlayer.loadNameOfMaps());
		
		maps.addItem("new");
		maps.setSelectedItem("new");
		
		carica = new JButtonCustom(ResourcesLoader.mainmenu_images.get(0).getScaledInstance(width, height, 0),
				ResourcesLoader.mainmenu_images.get(1).getScaledInstance(width, height, 0),
				ResourcesLoader.mainmenu_images.get(2).getScaledInstance(width, height, 0));

		add(exit);
		add(save);
		add(sfondo1);
		add(sfondo2);
		add(muro0);
		add(muro1);
		add(header);
		add(maps);
		add(carica);
		add(nomeMappa);
		
		
		setBounds(CenterComp.centerX(WIDTHSFONDO), CenterComp.centerY(HEIGHTSFONDO), WIDTHSFONDO, HEIGHTSFONDO);
		setOpaque(false);

		sfondo1.setBounds(distanzaLeft, 100, width, height);

		sfondo2.setBounds(distanzaLeft, 160, width, height);
		muro0.setBounds(distanzaLeft, 220, width, height);
		muro1.setBounds(distanzaLeft, 280, width, height);

		maps.setBounds(distanzaLeft, 360, 200, 70);
		carica.setBounds(distanzaLeft, 440, width + 20, height);
		nomeMappa.setBounds(distanzaLeft,540,150,30);
		exit.setBounds(distanzaLeft + 50, 600, width, height);
		save.setBounds(distanzaLeft - 10, 600, width, height);

		header.setBounds(distanzaLeft, 30, 700, 50);
		setVisible(true);
		repaint();
		listenerLoader();
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){	
					if(rectangle.contains(e.getX(), e.getY())){	
						if ( state >=0) {
							if (state == 0)
								nuovoMuro = new VerySquareWall(e.getX(), e.getY(), -1);
							if (state == 2)
								nuovoMuro = new VerySquareWall(e.getX(), e.getY(), 2);
		
							if (canAdd()) {
								wall.add(nuovoMuro);
								repaint();
							}
						}
						else if(state==-1)
							{clickSpawn(e.getPoint());
							
							state=-2;
							}
					}
					
				}
				if(e.getButton()==MouseEvent.BUTTON3){
					
					for(int i=0;i<wall.size();i++){
						if(wall.get(i).getRectangle().contains(e.getPoint())){
							wall.remove(i);
							repaint();
						}
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				clickSpawn(e.getPoint());
				
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
				
				if(state>=0){
					tmp.getOrigin().setLocation(e.getX(), e.getY());
					tmp.update();
				}
				repaint();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if(state==-1){
					players.get(indexSpwan).getOrigin().move(e.getX(), e.getY());
					players.get(indexSpwan).update();
					}
				
			repaint();
			}
		});
		
	}
	
	private void clickSpawn(Point p){
		
		if(state!=-1){
			for(int i=0;i<players.size();i++)
				{if(players.get(i).getShape().contains(p)){
					indexSpwan=i;
					state=-1;}
					}}
		else 
		{	
			players.get(indexSpwan).getOrigin().move(p.x, p.y);
			players.get(indexSpwan).update();
			rimuoviOstacoli(players.get(indexSpwan).getShape());
			repaint();
		}
	}

	private void rimuoviOstacoli(Shape shape) {
		for (int i=0;i<wall.size();i++){
			if (wall.get(i).getRectangle().intersects((Rectangle) shape))
			{	wall.remove(i);
				}	
			}
		
	}

	private String newNome() {
		int i=0;
		
		while(ManagerFilePlayer.mapExist("new"+i)){
			i++;
			}
		
		return "new"+i;
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		g.drawImage(ResourcesLoader.optionmenu_images.get(4), 0, 0, WIDTHSFONDO, HEIGHTSFONDO, null);
		g.drawImage(sfondoSelezionato, (int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(),
				(int) rectangle.getHeight(), null);
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < wall.size(); i++) {
			wall.get(i).paint(g2d);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).paint(g2d);
		}
		if(state>=0)
			tmp.paint(g2d);
	}

	@Override
	public void setVisible(boolean aFlag) {

		super.setVisible(aFlag);
		exit.setVisible(aFlag);
		sfondo1.setVisible(aFlag);
		sfondo2.setVisible(aFlag);
		muro0.setVisible(aFlag);
		muro1.setVisible(aFlag);
		maps.setVisible(aFlag);
		carica.setVisible(aFlag);
		save.setVisible(aFlag);
		nomeMappa.setVisible(aFlag);
	}

	public void showExitConfirm() {

		System.exit(0);

	}

	private boolean canAdd() {
		for (int i = 0; i < wall.size(); i++) {
			if (wall.get(i).getRectangle().intersects(nuovoMuro.getRectangle()))
				return false;
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getShape().intersects((nuovoMuro.getRectangle())))
				return false;
		}
		if (!rectangle.contains(nuovoMuro.getRectangle()))
			return false;

		return true;
	}

	private void listenerLoader() {
		muro0.addActionListener(e -> {
			state = 0;
			tmp=new VerySquareWall(-50, -50, -1);
			repaint();
		});
		;

		muro1.addActionListener(e -> {
			state = 2;
			 tmp=new VerySquareWall(-50, -50, 2);
			repaint();
		});

		sfondo1.addActionListener(e -> {
			attrito = 2;
			sfondoSelezionato = imageSfondo1;
			repaint();
		});
		sfondo2.addActionListener(e -> {
			attrito = 1;
			sfondoSelezionato = imageSfondo2;

			repaint();
		});
		exit.addActionListener(e -> {

			Chiudere();

		});
		save.addActionListener(e -> {
			if (!nomeMappa.getText().equals("new")) {

			ManagerFilePlayer.saveMap(attrito, wall,players, nomeMappa.getText(),rectangle.x,rectangle.y);
			Chiudere();
			}
		});

		carica.addActionListener(e -> {
			wall.removeAll(wall);
			if (!maps.getSelectedItem().equals("new")) {
				
				players.removeAll(players);
				if (ManagerFilePlayer.getAttritoMap((String) maps.getSelectedItem()) == 2) {
					attrito = 2;
					sfondoSelezionato = imageSfondo1;
				} else {
					attrito = 1;
					sfondoSelezionato = imageSfondo2;
				}
				
				
				wall.addAll(ManagerFilePlayer.getWallsInTheMap((String) maps.getSelectedItem(),rectangle.x,rectangle.y));

				for (int i = 0; i < wall.size(); i++) {
					wall.get(i).update();

				}
				
				players.addAll(ManagerFilePlayer.getSpawnInTheMap((String) maps.getSelectedItem(),rectangle.x,rectangle.y));
				for(int i=0;i<players.size();i++){
					players.get(i).update();
				}
			
			nomeMappa.setText ((String) maps.getSelectedItem());
			
			
			}
			
			repaint();
			});
			}

	

	public void Chiudere() {

		frame.getLayeredPane().getComponentsInLayer(1)[0].setEnabled(true);
		frame.getLayeredPane().remove(frame.getLayeredPane().getComponentsInLayer(2)[0]);

	}
}
