package it.batteringvalhalla.gamecore.IA;

import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.actor.Actor;

public class IAFocus extends AbstractMediumIA {

	public IAFocus(Actor pg, Arena arena) {
		super(pg, arena);
	
	}
	
	public void moveActor(){
		vaiContro(objects.get(0));
	}

}
