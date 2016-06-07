package it.batteringvalhalla.gamegui.editorActor;




import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;

import java.awt.Image;









public class ImageEditor  {

private static int IndexHead;
private static int IndexBody;
private static int IndexrRam;




public ImageEditor(){
	
	

	IndexHead=ManagerFilePlayer.getTop();
	IndexBody=ManagerFilePlayer.getMid();
	IndexrRam=ManagerFilePlayer.getBot();
	
	
	
	

}
public Image getImageBody() {
	return  ResourcesLoader.imageBust.get(IndexBody);
	}

public Image getImageBody1() {
	return  ResourcesLoader.imageBust.get(IndexBody+1);
	}
public Image getImageRam() {
	 
	return ResourcesLoader.imageGoat.get(IndexrRam);
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
public void spostaBody(int i){
	IndexBody=(IndexBody+(i*2))%ResourcesLoader.imageBust.size();
	
	if (IndexBody<0){
		IndexBody=+ResourcesLoader.imageBust.size()-1;
	}
}
public void spostaRam(int i){
	IndexrRam=(IndexrRam+i)%ResourcesLoader.imageGoat.size();
	if (IndexrRam<0){
		IndexrRam=+ResourcesLoader.imageGoat.size()-1;
	}
}


public static int getIndexBody() {
	return IndexBody;
}
public static int getIndexRam() {
	return IndexrRam;
}
public static int getIndexHead() {
	return IndexHead;
}
}