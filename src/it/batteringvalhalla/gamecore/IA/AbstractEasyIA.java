package it.batteringvalhalla.gamecore.IA;

import java.util.Random;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.actor.Actor;


public abstract class AbstractEasyIA {
protected Actor pg;
protected Arena arena;
protected Random rnd = new Random();
protected int next;

public AbstractEasyIA(Actor pg,Arena arena ){
	this.pg=pg;
	this.arena=arena;
	next=rnd.nextInt(4)+1;
}
protected boolean canNord(){
	
	if (arena.getEdge().contains(pg.getX(), pg.getY()-pg.getMaxSpeed()))
			return true;
	
		return false;
	
	
}


protected boolean canSud(){
	
	if (arena.getEdge().contains(pg.getX(), pg.getY()+pg.getMaxSpeed()))
		return true;
	return false;


}


protected boolean canEst(){
	
	if (arena.getEdge().contains(pg.getX()+pg.getMaxSpeed(), pg.getY()))
		return true;
	return false;


}


protected boolean canOvest(){
	
	if (arena.getEdge().contains(pg.getX()-pg.getMaxSpeed(), pg.getY()))
		return true;
	return false;


}
public  void moveActor() {


}
protected boolean warning(){
	if(!canEst()||!canNord()||!canOvest()||!canSud())
		return true;
	return false;
}

protected int whereICan(){
	int tmp=rnd.nextInt(4)+1;
	int i=0;
	while(i<6){
	switch (tmp) {
	case 1:
		if (canNord())
		{
			return tmp;
			
			
		}
		else
			tmp=rnd.nextInt(4)+1;
		break;
		
	case 2:
		if (canSud())
			{return tmp;
			}
		else
			tmp=rnd.nextInt(4)+1;
		break;
	case 3:
		if (canEst())
			{return tmp;
			}
		else 
			tmp=rnd.nextInt(4)+1;
		break;
	case 4:
		if (canOvest())
			{return tmp;
			}
		else
			tmp=rnd.nextInt(4)+1;
		break;
	case 5:

		return tmp;
		

	default:

		break;}
	i++;}
return 5;
}
}

