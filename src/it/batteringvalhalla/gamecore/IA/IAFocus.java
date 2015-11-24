package it.batteringvalhalla.gamecore.IA;






import java.util.List;


import it.batteringvalhalla.gamecore.arena.Arena;
import it.batteringvalhalla.gamecore.object.actor.Actor;
import it.batteringvalhalla.gamecore.object.actor.Direction;
import it.batteringvalhalla.gamecore.object.actor.Enemy;
import it.batteringvalhalla.gamecore.object.AbstractGameObject;

public class IAFocus extends AbstractIA {
	private static final int timeMove = 2000;
	private  int timePause;
	private AbstractGameObject myEnemy;
	private List<AbstractGameObject> players; 
	
	public IAFocus(Enemy npc, Arena arena,List<AbstractGameObject> players,Actor actor) {
		super(npc, arena);
		this.players= players;
		timePause=1000;
		
		}	
	
	protected AbstractGameObject getEnemyClose() {
		AbstractGameObject tmp=null;
		
		int radiusTmp,radiusPlayer; 
		for(int i=0;i<players.size();i++){
			if(npc!=players.get(i)){
				if(tmp!=null){
					radiusTmp=Math.abs(tmp.getX()-npc.getX())+Math.abs(tmp.getY()-npc.getY());
					radiusPlayer=Math.abs(players.get(i).getX()-npc.getX())+Math.abs(players.get(i).getY()-npc.getY());
					if(radiusTmp>radiusPlayer)
						tmp=players.get(i);
				}
				else 
					tmp=players.get(i);}}
		return tmp;
	}
	
	
	public void levelUp(){
	if(timePause>199)
		timePause-=200;
	}
	public void update(){
		myEnemy=getEnemyClose();
		
		
		currentime = System.currentTimeMillis();
		
		if(npc.getCollisionShape().intersects(myEnemy.getCollisionShape()))
			{startime=Math.abs((long)timeMove-currentime);
			
			}
		
			
		 if (currentime - startime <timeMove&&(this.canMove(npc.getX(), npc.getY()))){
			
			if(Math.abs(myEnemy.getX()-npc.getX())>=Math.abs(myEnemy.getY()-npc.getY())){
				if(myEnemy.getX()>npc.getX())
					npc.setDirection(Direction.est);
				else
					npc.setDirection(Direction.ovest);
				}
			else {	
				if(myEnemy.getY()>npc.getY())
					npc.setDirection(Direction.sud);
				else 
					npc.setDirection(Direction.nord);
		}
		}
		else if (currentime-startime>=timeMove+timePause){
			startime=currentime;
			}
		
	}



}
