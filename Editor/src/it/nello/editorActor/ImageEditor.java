package it.nello.editorActor;



import java.awt.Image;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.STRING;


public class ImageEditor  {
private static List<Image> imageTesta;
private static List<Image> imageBusto;
private static List<Image> imageCapra;
private static Image tmp;
private static Image frecciaDestra;
private static Image frecciaSinistra;
private static Image Sfondo;
private static List<Image> exit;
private static Image save;
private static int IndexTesta;
private static int IndexBusto;
private static int IndexCapra;
private static int IndexExit;
private static boolean ErrorFrecce; 
private static boolean finito=false;

public ImageEditor(){
	ErrorFrecce=false;
	imageTesta=new ArrayList<Image>();
	imageBusto=new ArrayList<Image>();
	imageCapra=new ArrayList<Image>();
	exit=new ArrayList<Image>();
	IndexBusto=IndexCapra=IndexTesta=IndexExit=0;
	try {
		save=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/save.png"));
		
		frecciaDestra=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/frecciaDestra.png"));
		frecciaSinistra=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/frecciaSinistra.png"));
		Sfondo=ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/sfondo.png"));
		
	}
	catch(final IOException e){
		ErrorFrecce=true;
	}
	for(int i=0;i<2;i++){
	 try{
		exit.add(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/exit"+i+".png")));
	}
	 catch(final IOException e){
		 
	 }}
	
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
	return save;
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
	IndexExit=(IndexExit+1)%imageCapra.size();
	
}
public void reset(){
	IndexExit=0;
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