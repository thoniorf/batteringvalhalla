package it.batteringvalhalla.gamegui.editorActor;




import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

import java.awt.Image;









public class ImageEditor  {

private static int IndexHead;
private static int IndexBust;
private static int IndexGoat;




public ImageEditor(){
	
	

	IndexHead=ManagerFilePlayer.getTop();
	IndexBust=ManagerFilePlayer.getMid();
	IndexGoat=ManagerFilePlayer.getBot();
	
	
	
	

}
public Image getImageBust() {
	return  ResourcesLoader.imageBust.get(IndexBust);
	}

public Image getImageBust1() {
	return  ResourcesLoader.imageBust.get(IndexBust+1);
	}
public Image getImageGoat() {
	 
	return ResourcesLoader.imageGoat.get(IndexGoat);
}

public Image getImageHead() {
	return ResourcesLoader.imageHead.get(IndexHead);
}




	

public void spostaHead(int i){
	IndexHead=(IndexHead+i)%ResourcesLoader.imageHead.size();
	if (IndexHead<0){
		IndexHead=+ResourcesLoader.imageHead.size()-1;
	}

	}
public void spostaBust(int i){
	IndexBust=(IndexBust+(i*2))%ResourcesLoader.imageBust.size();
	
	if (IndexBust<0){
		IndexBust=+ResourcesLoader.imageBust.size()-1;
	}
}
public void spostaGoat(int i){
	IndexGoat=(IndexGoat+i)%ResourcesLoader.imageGoat.size();
	if (IndexGoat<0){
		IndexGoat=+ResourcesLoader.imageGoat.size()-1;
	}
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