package it.batteringvalhalla.gamecore.IA;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.actor.Actor;
import it.batteringvalhalla.gamecore.object.actor.Direction;

public class IAWalk extends AbstractEasyIA {
	private int next;
	public  IAWalk(Actor pg,Arena arena){
		
		super(pg,arena);
		next=rnd.nextInt(5)+1;
	}
	public void moveActor(){
	
		if(warning())
			next=whereICan();
			
		
		switch (next) {
		case 1:
			
				pg.setDirection(Direction.nord);
				break;
				
			
			
		case 2:
			pg.setDirection(Direction.sud);;
				break;
			
		case 3:
		pg.setDirection(Direction.est);;
				break;

		case 4:
			
				pg.setDirection(Direction.ovest);;
				break;
			
		case 5:

			pg.setDirection(Direction.stop);
			break;

		default:

			break;}
	}

		//boolean speed=rnd.nextBoolean();
		
		
			}
	

