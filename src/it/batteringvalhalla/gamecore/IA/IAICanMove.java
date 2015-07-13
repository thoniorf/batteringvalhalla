package it.batteringvalhalla.gamecore.IA;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.actor.Actor;
import it.batteringvalhalla.gamecore.object.actor.Direction;

public class IAICanMove extends AbstractEasyIA {
	public  IAICanMove(Actor pg,Arena arena){
		super(pg,arena);
	}
	public void moveActor(){
		 next=rnd.nextInt(5)+1;
		//boolean speed=rnd.nextBoolean();

		switch (next) {
		case 1:
			if (canNord())
			{
				pg.setDirection(Direction.nord);
				break;
				
			}
			else
				next=5;
			
		case 2:
			if (canSud())
				{pg.setDirection(Direction.sud);;
				break;}
			else
				next=5;
		case 3:
			if (canEst())
				{pg.setDirection(Direction.est);;
				break;}
			else 
				next=5;
		case 4:
			if (canOvest())
				{pg.setDirection(Direction.ovest);;
				break;}
			else
				next=5;
		case 5:

			pg.setDirection(Direction.stop);
			break;

		default:

			break;}}

	
	

}
