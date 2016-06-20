package it.batteringvalhalla.gamecore.collision;

import it.batteringvalhalla.gamecore.loader.ManagerFilePlayer;
import it.batteringvalhalla.gamecore.object.AbstractEntity;
import it.batteringvalhalla.gamecore.object.Entity;
import it.batteringvalhalla.gamecore.object.actor.AbstractActor;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;
import it.batteringvalhalla.gamecore.vector2d.Vector2D;
import it.batteringvalhalla.gamegui.sound.Sound;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
	private static List<Entity> objects = null;
	private static boolean collisioned;
	

	public static void setObjects(List<Entity> objects) {
		CollisionHandler.objects = new ArrayList<>(objects);
		
	}

	public static void check() {
		// check loop
		collisioned=false;
		for (int i = 0; i < (objects.size() - 1); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				if (objects.get(i).getAlive() && objects.get(j).getAlive()) {
					// detect collision
					if (detect(objects.get(i), objects.get(j))) {
						collisioned=true;
						// resolve
						resolve((AbstractEntity) objects.get(i), (AbstractEntity) objects.get(j));
					}
				}

			}
		}
	}

	private static boolean detect(Entity first, Entity second) {
		// return shape intersection
		return first.getShape().intersects((Rectangle2D) second.getShape());
	}

	private static Vector2D penetrationDepth(AbstractEntity first, AbstractEntity second) {
		// collision axis
		int x_depth = 0;
		int y_depth = 0;
		// penetration side
		if (first.getOrigin().x <= second.getOrigin().x) {
			// LEFT
			x_depth = ((second.getX() - (second.getWidth() / 2)) - (first.getX() + (first.getWidth() / 2))) - 1;
		} else {
			// RIGHT
			x_depth = ((second.getX() + (second.getWidth() / 2)) - (first.getX() - (first.getWidth() / 2))) + 1;
		}
		if (first.getOrigin().y <= second.getOrigin().y) {
			// TOP
			y_depth = (second.getY() - (second.getHeight() / 2)) - (first.getY() + (first.getHeight() / 2)) - 1;
		} else {
			// BOTTOM
			y_depth = (second.getY() + (second.getHeight() / 2)) - (first.getY() - (first.getHeight() / 2)) + 1;
		}
		return new Vector2D(x_depth, y_depth);
	}

	private static Vector2D computeMtd(AbstractEntity first, AbstractEntity second) {
		// init minimum translation distance vector
		Vector2D mtd = new Vector2D();
		// compute penetration depth
		Vector2D depth = penetrationDepth(first, second);
		// set mtd values
		if (Math.abs(depth.getComponents().x) <= Math.abs(depth.getComponents().y)) {
			mtd.setX(depth.getComponents().x);
		}
		if (Math.abs(depth.getComponents().y) <= Math.abs(depth.getComponents().x)) {
			mtd.setY(depth.getComponents().y);
		}
		// return the mtd vector
		return mtd;
	}

	private static void resolve(AbstractEntity thisone, AbstractEntity otherone) {
		if (thisone instanceof VerySquareWall && otherone instanceof VerySquareWall) {
			return;
		}
		playCollision();

		Vector2D newVel_first = new Vector2D();
		Vector2D newVel_second = new Vector2D();

		if (thisone instanceof AbstractActor) {
			// get the mtd vector
			Vector2D mtd = computeMtd(thisone, otherone);
			AbstractActor a1 = (AbstractActor) thisone;
			// fix overlap
			thisone.getOrigin().translate(mtd.getComponents().x, mtd.getComponents().y);
			// old velocity
			Vector2D oldVel_first = new Vector2D(a1.getVelocity());
			if (otherone instanceof AbstractActor) {
				AbstractActor a2 = (AbstractActor) otherone;
				// old velocity
				Vector2D oldVel_second = new Vector2D(a2.getVelocity());

				// check if mtd is along x-axis
				if (mtd.getComponents().x != 0) {
					if (oldVel_second.getX() != 0) {
						newVel_first = new Vector2D((oldVel_first.getX() + (2 * oldVel_second.getX())), a1.getVelocity().getY());
						newVel_second = new Vector2D(oldVel_second.getX() + (2 * oldVel_first.getX()), a2.getVelocity().getY());
					} else {
						newVel_first = new Vector2D((oldVel_first.getX() + -1 + (2 * oldVel_second.getX())), a1.getVelocity()
								.getY());
						newVel_second = new Vector2D(oldVel_second.getX() + (2 * oldVel_first.getX()), a2.getVelocity().getY());
					}

					a1.setVelocity(newVel_first);
					a2.setVelocity(newVel_second);
				}
				// check if mtd is along y-axis
				if (mtd.getComponents().y != 0) {
					if (oldVel_second.getY() != 0) {
						newVel_first = new Vector2D(a1.getVelocity().getX(), (oldVel_first.getY() + (2 * oldVel_second.getY())));
						newVel_second = new Vector2D(a2.getVelocity().getX(), oldVel_second.getY() + (2 * oldVel_first.getY()));
					} else {
						newVel_first = new Vector2D(a1.getVelocity().getX(),
								(oldVel_first.getY() * -1 + (2 * oldVel_second.getY())));
						newVel_second = new Vector2D(a2.getVelocity().getX(), oldVel_second.getY() + (2 * oldVel_first.getY()));
					}
					a1.setVelocity(newVel_first);
					a2.setVelocity(newVel_second);
				}
			} else if (otherone instanceof VerySquareWall) {
				// check if mtd is along x-axis
				if (mtd.getComponents().x != 0) {
					a1.setVelocity(new Vector2D((oldVel_first.getComponents().x) * -1, a1.getVelocity().getComponents().y));

				}
				// check if mtd is along y-axis
				if (mtd.getComponents().y != 0) {
					a1.setVelocity(new Vector2D(a1.getVelocity().getComponents().x, (oldVel_first.getComponents().y) * -1));

				}
			}
		} else if (thisone instanceof VerySquareWall) {
			if (otherone instanceof AbstractActor) {
				// get the mtd vector
				Vector2D mtd = computeMtd(otherone, thisone);
				AbstractActor a2 = (AbstractActor) otherone;
				// fix overlap
				otherone.getOrigin().translate(mtd.getComponents().x, mtd.getComponents().y);
				// old velocity
				Vector2D oldVel_second = new Vector2D(a2.getVelocity());
				// check if mtd is along x-axis
				if (mtd.getComponents().x != 0) {
					a2.setVelocity(new Vector2D((oldVel_second.getComponents().x) * -1, a2.getVelocity().getComponents().y));

				}
				// check if mtd is along y-axis
				if (mtd.getComponents().y != 0) {
					a2.setVelocity(new Vector2D(a2.getVelocity().getComponents().x, (oldVel_second.getComponents().y) * -1));

				}
			}
		}
	}

	public static void playCollision() {
		if (ManagerFilePlayer.soundOn()) {
			Sound.collision().setFramePosition(0);
			Sound.collision().start();
		}
	}

	public CollisionHandler() {
	}

	public static Boolean isCollisioned(){
		return collisioned;
	}
}
