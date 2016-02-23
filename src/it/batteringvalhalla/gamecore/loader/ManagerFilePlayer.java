package it.batteringvalhalla.gamecore.loader;

import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class ManagerFilePlayer {
private static File f;
private static int top,mid,bot;
private static String sound;
private static String name;
private final int  ASCIICODE;
private static File dir;
public ManagerFilePlayer() {
	
	ASCIICODE=48;
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
public static void saveMap(double attrito, List<VerySquareWall> wall) {
	Calendar gc=Calendar.getInstance();
	try{
		File f1=new File(dir.getName()+"/Maps");
		if(!f1.exists()){
			f1.mkdir();
		}
		FileWriter w=new FileWriter(f1+"/"+ManagerFilePlayer.getName()+" "+gc.get(Calendar.DAY_OF_MONTH)+" "+Integer.toString(gc.get(Calendar.MONTH+1))+" "+gc.get(Calendar.YEAR)+" "+gc.get(Calendar.HOUR)+" "+gc.get(Calendar.MINUTE)+" "+gc.get(Calendar.SECOND));
		BufferedWriter bw=new BufferedWriter(w);
	
		bw.write(attrito+"\n");
		for (int i=0;i<wall.size();i++ ){
			bw.write(wall.get(i).getX()+"\n");
			bw.write(wall.get(i).getY()+"\n");
			bw.write(wall.get(i).getMaxLife()+"\n");
		}
		
	    bw.flush();
		
		bw.close();
	}catch(IOException c){
		c.printStackTrace();
	}
}

}
