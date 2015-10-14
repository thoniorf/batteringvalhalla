package it.batteringvalhalla.gamegui.editorActor;



import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

import java.awt.Image;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;



public class ImageEditor  {
private static List<Image> imageTesta;
private static List<Image> imageBusto;
private static List<Image> imageCapra;

private static Image frecciaDestra;
private static Image frecciaSinistra;
private static Image Sfondo;
private static List<Image> exit;
private static List<Image> save;
private static int IndexTesta;
private static int IndexBusto;
private static int IndexCapra;
private static int IndexExit;
private static int IndexSave;
private static boolean ErrorFrecce; 
private static boolean finito=false;


public ImageEditor(){
	
	ErrorFrecce=false;
	imageTesta=new ArrayList<Image>();
	imageBusto=new ArrayList<Image>();
	imageCapra=new ArrayList<Image>();
	exit=new ArrayList<Image>();
	save=new ArrayList<Image>();
	IndexTesta=ManagerFilePlayer.getTop();
	IndexBusto=ManagerFilePlayer.getMid();
	IndexCapra=ManagerFilePlayer.getBot();
	IndexSave=IndexExit=0;
	
    save.add(ResourcesLoader.exitmenu_images.get(1));
    save.add(ResourcesLoader.exitmenu_images.get(3));
	try {
		
		
		frecciaDestra=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/frecciaDestra.png"));
		frecciaSinistra=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/frecciaSinistra.png"));
		Sfondo=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/sfondo.png"));
		
	}
	catch(final IOException e){
		ErrorFrecce=true;
	}
	
	 
		exit.add( ResourcesLoader.optionmenu_images.get(5));
		exit.add( ResourcesLoader.optionmenu_images.get(6));
	
	
	for(int i=0;i<4&&!finito;i++){
		try{
			
			imageTesta.add(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/testa"+i+".jpg")));
		}
		catch(final IOException e){
			finito=true;}

		}
	
	finito=false;
	
	for(int i=0;i<4&&!finito;i++){
		try{
			imageBusto.add(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Busto"+i+".jpg")));
		}
		catch(final IOException e){
			finito=true;}
		}
	
	finito=false;
	
	for(int i=0;i<3&&!finito;i++){
		try{
			
			imageCapra.add(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/Capra"+i+".png")));
		}
		catch(final IOException e){
			finito=true;}
		}}

//i get tornano un elemento!!
public Image getImageBusto() {
	return imageBusto.get(IndexBusto);
	}

public Image getImageCapra() {
	return imageCapra.get(IndexCapra);
}

public Image getImageTesta() {
	return imageTesta.get(IndexTesta);
}

public Image getFrecciaDestra() {
	return frecciaDestra;
}

public  Image getFrecciaSinistra() {
	return frecciaSinistra;
}
public Image getSfondo() {
	return Sfondo;
}

public Image getExit() {
	return exit.get(IndexExit);
}
public Image getSave() {
	return save.get(IndexSave);
}

boolean Error(){
	if(ErrorFrecce||imageBusto.isEmpty()||imageCapra.isEmpty()||imageTesta.isEmpty())
		return true;
	else 
		return false;}

public void spostaTesta(int i){
	IndexTesta=(IndexTesta+i)%imageTesta.size();
	if (IndexTesta<0){
		IndexTesta=+imageTesta.size()-1;
	}}
public void spostaBusto(int i){
	IndexBusto=(IndexBusto+i)%imageBusto.size();
	if (IndexBusto<0){
		IndexBusto=+imageBusto.size()-1;
	}
}
public void spostaCapra(int i){
	IndexCapra=(IndexCapra+i)%imageCapra.size();
	if (IndexCapra<0){
		IndexCapra=+imageCapra.size()-1;
	}
}
void pushExit(){
	IndexExit=(IndexExit+1)%2;
	
}
public void resetExit(){
	IndexExit=0;
}
void pushSave(){
	IndexSave=(IndexSave+1)%2;
	
}
public void resetSave(){
	IndexSave=0;
}
public String getNameTesta(){
	return "testa"+IndexTesta+".png";
}
public String getNameBusto(){
	return "testa"+IndexBusto+".png";
}
public String getNameCapra(){
	return "testa"+IndexCapra+".png";
}
public static int getIndexBusto() {
	return IndexBusto;
}
public static int getIndexCapra() {
	return IndexCapra;
}
public static int getIndexTesta() {
	return IndexTesta;
}
}