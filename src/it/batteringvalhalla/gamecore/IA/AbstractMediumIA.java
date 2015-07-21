package it.batteringvalhalla.gamecore.IA;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.AbstractGameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;
import it.batteringvalhalla.gamecore.object.actor.Direction;

public abstract class AbstractMediumIA extends AbstractEasyIA{
	protected List<AbstractGameObject> objects;
	
	public AbstractMediumIA(Actor pg, Arena arena) {
		super(pg, arena);
		
	}
	
	public void setList(List<AbstractGameObject> obj){
		objects=new  CopyOnWriteArrayList<AbstractGameObject>();
		objects.addAll(obj);
	}
	
	protected AbstractGameObject vicino(){
		for(int i=0 ;i<objects.size();i++){
			if(Math.abs(pg.getX()-objects.get(i).getX())<1&&Math.abs(pg.getY()-objects.get(i).getY())<1)
				return objects.get(i);}
		return null;	
	}
	
	protected void vaiContro(AbstractGameObject abo){
		if(pg.getX()>abo.getX())
			pg.setDirection(Direction.ovest);
		if(pg.getX()<abo.getX())
			pg.setDirection(Direction.est);
		if(pg.getY()>abo.getY())
			pg.setDirection(Direction.nord);
		if(pg.getY()<abo.getY())
			pg.setDirection(Direction.sud);
				
		
	}

}
