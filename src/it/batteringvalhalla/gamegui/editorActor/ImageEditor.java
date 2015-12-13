package it.batteringvalhalla.gamegui.editorActor;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

public class ImageEditor {
	// private static List<Image> imageTesta;
	// private static List<Image> imageBusto;
	// private static List<Image> imageCapra;
	//
	// private static Image frecciaDestra;
	// private static Image frecciaSinistra;
	// private static Image Sfondo;
	// private static List<Image> exit;
	// private static List<Image> save;
	private static int IndexHead;
	private static int IndexBust;
	private static int IndexGoat;
	private static int IndexExit;
	private static int IndexSave;
	// private static boolean ErrorFrecce;
	// private static boolean finito=false;
	private static List<Integer> indexArrow;

	public static List<Integer> getIndexArrow() {
		return indexArrow;
	}

	public ImageEditor() {

		// imageTesta=new ArrayList<Image>();
		// imageBusto=new ArrayList<Image>();
		// imageCapra=new ArrayList<Image>();
		// exit=new ArrayList<Image>();
		// save=new ArrayList<Image>();
		IndexHead = ManagerFilePlayer.getTop();
		IndexBust = ManagerFilePlayer.getMid();
		IndexGoat = ManagerFilePlayer.getBot();
		IndexSave = IndexExit = 0;
		indexArrow = new ArrayList<Integer>(6);

		// save.add(ResourcesLoader.exitmenu_images.get(1));
		// save.add(ResourcesLoader.exitmenu_images.get(3));
		// try {
		//
		//
		// frecciaDestra=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/frecciaDestra.png"));
		// frecciaSinistra=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/frecciaSinistra.png"));
		// Sfondo=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/sfondo.png"));
		//
		// }
		// catch(final IOException e){
		// ErrorFrecce=true;
		// }
		//
		//
		// exit.add( ResourcesLoader.optionmenu_images.get(5));
		// exit.add( ResourcesLoader.optionmenu_images.get(6));
		//
		//
		// for(int i=0;i<4&&!finito;i++){
		// try{
		//
		// imageTesta.add(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/testa"+i+".jpg")));
		// }
		// catch(final IOException e){
		// finito=true;}
		//
		// }
		//
		// finito=false;
		//
		// for(int i=0;i<4&&!finito;i++){
		// try{
		// imageBusto.add(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Busto"+i+".jpg")));
		// }
		// catch(final IOException e){
		// finito=true;}
		// }
		//
		// finito=false;
		//
		// for(int i=0;i<3&&!finito;i++){
		// try{
		//
		// imageCapra.add(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Capra"+i+".png")));
		// }
		// catch(final IOException e){
		// finito=true;}
		// }}
		//
		// i get tornano un elemento!!
	}

	public Image getImageBust() {
		return ResourcesLoader.imageBust.get(IndexBust);
	}

	public Image getImageGoat() {
		return ResourcesLoader.imageGoat.get(IndexGoat);
	}

	public Image getImageHead() {
		return ResourcesLoader.imageHead.get(IndexHead);
	}

	public Image getFrecciaDestra() {
		return ResourcesLoader.rightArrow.get(0);
	}

	public Image getFrecciaSinistra() {
		return ResourcesLoader.leftArrow.get(0);
	}

	public Image getSfondo() {
		return ResourcesLoader.Sfondo;
	}

	public Image getExit() {
		return ResourcesLoader.mainmenu_images.get(IndexExit);
	}

	public Image getSave() {
		return ResourcesLoader.mainmenu_images.get(IndexSave + 4);
	}

	// boolean Error(){
	// if(ErrorFrecce||imageBusto.isEmpty()||imageCapra.isEmpty()||imageTesta.isEmpty())
	// return true;
	// else
	// return false;}

	public void spostaHead(int i) {
		IndexHead = (IndexHead + i) % ResourcesLoader.sizeHead;
		if (IndexHead < 0) {
			IndexHead = +ResourcesLoader.sizeHead - 1;
		}
	}

	public void spostaBust(int i) {
		IndexBust = (IndexBust + i) % ResourcesLoader.sizeBust;
		if (IndexBust < 0) {
			IndexBust = +ResourcesLoader.sizeBust - 1;
		}
	}

	public void spostaGoat(int i) {
		IndexGoat = (IndexGoat + i) % ResourcesLoader.sizeGoat;
		if (IndexGoat < 0) {
			IndexGoat = +ResourcesLoader.sizeGoat - 1;
		}
	}

	void pushExit() {
		IndexExit = (IndexExit + 1) % 2;

	}

	public void resetExit() {
		IndexExit = 0;
	}

	void pushSave() {
		IndexSave = (IndexSave + 1) % 2;

	}

	public void resetSave() {
		IndexSave = 0;
	}

	public String getNameTesta() {
		return "testa" + IndexHead + ".png";
	}

	public String getNameBusto() {
		return "testa" + IndexBust + ".png";
	}

	public String getNameCapra() {
		return "testa" + IndexGoat + ".png";
	}

	public static int getIndexBusto() {
		return IndexBust;
	}

	public static int getIndexCapra() {
		return IndexGoat;
	}

	public static int getIndexTesta() {
		return IndexHead;
	}
}