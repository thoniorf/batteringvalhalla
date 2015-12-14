package it.batteringvalhalla.gamegui.editorActor;




import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

import java.awt.Image;









public class ImageEditor  {

private static int IndexHead;
private static int IndexBust;
private static int IndexGoat;
private static int IndexExit;
private static int IndexSave;



public ImageEditor(){
	
	

	IndexHead=ManagerFilePlayer.getTop();
	IndexBust=ManagerFilePlayer.getMid();
	IndexGoat=ManagerFilePlayer.getBot();
	IndexSave=IndexExit=0;
	
	
	

}
public Image getImageBust() {
	return  ResourcesLoader.imageBust.get(0);
	}

public Image getImageGoat() {
	 
	return ResourcesLoader.imageGoat.get(IndexGoat);
}

public Image getImageHead() {
	return ResourcesLoader.imageHead.get(IndexHead);
}


public Image getSfondo() {
	return ResourcesLoader.Sfondo;
}

public Image getExit() {
	return ResourcesLoader.mainmenu_images.get(IndexExit);
}
public Image getSave() {
	return ResourcesLoader.mainmenu_images.get(IndexSave+4);
}

	

public void spostaHead(int i){
	

	}
public void spostaBust(int i){
	IndexBust=(IndexBust+i)%ResourcesLoader.actor_weapon.size();
	if (IndexBust<0){
		IndexBust=+ResourcesLoader.sizeBust-1;
	}
}
public void spostaGoat(int i){
	IndexGoat=(IndexGoat+i)%ResourcesLoader.actor_mount.size();
	if (IndexGoat<0){
		IndexGoat=+ResourcesLoader.sizeGoat-1;
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
	return "testa"+IndexHead+".png";
}
public String getNameBusto(){
	return "testa"+IndexBust+".png";
}
public String getNameCapra(){
	return "testa"+IndexGoat+".png";
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