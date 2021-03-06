package it.batteringvalhalla.gamecore.loader;

import it.batteringvalhalla.gamecore.object.actor.player.Player;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class ManagerFilePlayer {
private static File f;
private static int top,mid,bot;
private static String sound;
private static String name;
private final static int  ASCIICODE=48;
private static File dir;
private static File maps;
public ManagerFilePlayer() {
	
	
	dir=new File("Conf");
	if(!dir.exists())
		dir.mkdir();
	
    f=new File (dir.getName()+"/Save.txt");
    if(f.exists()){
    	load();
    }
    else {
    	try{
    		f.createNewFile();
    		top=mid=bot=0;
    		sound="0";
    		name="Player1";
    		save();
    		
    	}catch (IOException e) {
    		e.printStackTrace();
    	} }
     maps=new File(dir.getName()+"/Maps");
	if(!maps.exists()){
		maps.mkdir();
	}
    }
public void load(){
	String s;
	try{
		FileReader r=new FileReader(dir.getName()+"/"+f.getName());
		
		BufferedReader br = new BufferedReader(r);
		s= br.readLine();
		top=s.charAt(0)-ASCIICODE;
		mid=s.charAt(1)-ASCIICODE;
		bot=s.charAt(2)-ASCIICODE;
		
		sound=br.readLine();
		name=br.readLine();
		br.close();
	}
	catch (IOException e) {
		e.printStackTrace();
	}}

public static void save(){
	try{
		FileWriter w=new FileWriter(dir.getName()+"/"+f.getName());
		BufferedWriter bw=new BufferedWriter(w);
		
		bw.write(top+""+mid+""+bot+"\n");
		bw.write(sound+"\n");
		bw.write(name);
		
	    bw.flush();
		
		bw.close();
	}catch(IOException e){
		e.printStackTrace();
	}
}


public static int getTop(){
	return top;
}

public static int getMid() {
	return mid;
}
public static int getBot() {
	return bot;
}
public static void setTop(int t){
	top=t;
}
public static void setMid(int m) {
	mid =m;
}
public static void setBot(int b) {
	bot = b;
}

public static void setSound(String s) {
	sound = s;
}

public static String getSound() {
	return sound;
}

public static boolean soundOn(){
	if(sound.equals("1"))
		return true;
	return false;
}

public static String getName() {
	return name;
}
public static void setName(String name) {
	ManagerFilePlayer.name = name;
	save();
}
public static void saveMap(Integer attrito, List<VerySquareWall> wall, List<Player> players, String nomeMap,Integer x, Integer y) {
//TODO Calendar gc=Calendar.getInstance();
	try{
		FileWriter w;
//		if(nomeMap.equals("new"))
//			w=new FileWriter(maps+"/"+ManagerFilePlayer.getName()+" "+gc.get(Calendar.DAY_OF_MONTH)+" "+Integer.toString(gc.get(Calendar.MONTH+1))+" "+gc.get(Calendar.YEAR)+" "+gc.get(Calendar.HOUR)+" "+gc.get(Calendar.MINUTE)+" "+gc.get(Calendar.SECOND));
		
			w=new FileWriter(maps+"/"+nomeMap);
		BufferedWriter bw=new BufferedWriter(w);
	
		bw.write(attrito+"\n");
		for (int i=0;i<players.size();i++){
			bw.write(((int)players.get(i).getOrigin().getX())-x+"\n");
			bw.write(((int)players.get(i).getOrigin().getY())-y+"\n");
			}
		
		for (int i=0;i<wall.size();i++ ){
			bw.write(wall.get(i).getOrigin().x-x+"\n");
			bw.write(wall.get(i).getOrigin().y-y+"\n");
			bw.write(wall.get(i).getMaxLife()+"\n");
		}
		
	    bw.flush();
		
		bw.close();
	}catch(IOException c){
		c.printStackTrace();
	}
}
public static String[] loadNameOfMaps() {
	
	File file=new File(dir.getName()+"/Maps");
	if(!f.exists()){
		f.mkdir();
	}
	return file.list();
}
private static List<String> getMap(String selectedItem) {
	
	List <String >  lista=new ArrayList<String>();
	try {
		FileReader r = new FileReader(dir.getName()+"/"+maps.getName()+"/"+selectedItem);
		BufferedReader br = new BufferedReader(r);
		String s;
		
		s=br.readLine();
		
		while (s!=null){
			lista.add(s);
			
			s=br.readLine();}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return lista;
}
public static List<VerySquareWall> getWallsInTheMap(String selectedItem,Integer x,Integer y) {
	List <String> listaMuri=new ArrayList<String>();
	listaMuri.addAll(getMap(selectedItem));
	
	List<VerySquareWall> wall=new ArrayList<VerySquareWall>();
	
	for(int i=9;i+2<listaMuri.size();i+=3){
		
		wall.add(new VerySquareWall(Integer.decode(listaMuri.get(i))+x, Integer.decode(listaMuri.get(i+1))+y, Integer.decode(listaMuri.get(i+2))));
	}
	return wall;
}
public static Integer getAttritoMap(String selectedItem) {
	List <String> listaMuri=new ArrayList<String>();
	listaMuri.addAll(getMap(selectedItem));
	return Integer.decode(listaMuri.get(0));
}

public static boolean mapExist(String string) {
	File file;
	file = new File(dir.getName()+"/"+maps.getName()+"/"+string);
	if(file.exists())
			return true;
	else 
		return false;
	
}
public static List<Player> getSpawnInTheMap(String selectedItem,Integer x,Integer y) {
	List <String> listaSpawn=new ArrayList<String>();
	listaSpawn.addAll(getMap(selectedItem));
	
	List<Player> players=new ArrayList<Player>();
	
	for(int i=1;i+1<9;i+=2){
		
		players.add(new Player(new Point((Integer.decode(listaSpawn.get(i)))+x, Integer.decode(listaSpawn.get(i+1))+y)));
		
		}
	return players;
}

}
