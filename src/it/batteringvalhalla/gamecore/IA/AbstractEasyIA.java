package it.batteringvalhalla.gamecore.IA;

import java.util.Random;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.actor.Actor;

abstract class AbstractEasyIA {
protected Actor pg;
protected Arena arena;
protected Random rnd = new Random();

public AbstractEasyIA(Actor pg,Arena arena ){
	this.pg=pg;
	this.arena=arena;
}
protected boolean canNord(){
	
		if(pg.getY()-pg.getMaxSpeed()>arena.getEdge().y)
			return true;
	
		return false;
	
	
}


protected boolean canSud(){
	
	if(pg.getY()+pg.getMaxSpeed()<arena.getEdge().y+arena.getEdge().getHeight())
		return true;
	return false;


}


protected boolean canEst(){
	
	if(pg.getX()+pg.getMaxSpeed()<arena.getEdge().x+arena.getEdge().getWidth())
		return true;
	return false;


}


protected boolean canOvest(){
	
	if(pg.getX()-pg.getMaxSpeed()>arena.getEdge().x)
		return true;
	return false;


}


}

