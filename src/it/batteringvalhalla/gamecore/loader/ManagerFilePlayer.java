package it.batteringvalhalla.gamecore.loader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManagerFilePlayer {
private static File f;
private static int top,mid,bot;
private static String sound;
private final int  ASCIICODE;

public ManagerFilePlayer() {
	
	ASCIICODE=48;
    f=new File ("Save.txt");
    if(f.exists()){
    	load();
    }
    else {
    	try{
    		f.createNewFile();
    		top=mid=bot=0;
    		sound="0";
    		save();
    		
    	}catch (IOException e) {
    		e.printStackTrace();
    	} }
    }
public void load(){
	String s;
	try{
		FileReader r=new FileReader(f.getName());
		BufferedReader br = new BufferedReader(r);
		s= br.readLine();
		top=s.charAt(0)-ASCIICODE;
		mid=s.charAt(1)-ASCIICODE;
		bot=s.charAt(2)-ASCIICODE;
		
		sound=br.readLine();
	
		br.close();
	}
	catch (IOException e) {
		e.printStackTrace();
	}}

public static void save(){
	try{
		FileWriter w=new FileWriter(f.getName());
		BufferedWriter bw=new BufferedWriter(w);
	
		bw.write(top+""+mid+""+bot+"\n");
		bw.write(sound);
		
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

}
