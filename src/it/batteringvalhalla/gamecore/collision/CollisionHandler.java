package it.batteringvalhalla.gamecore.collision;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import it.batteringvalhalla.gamecore.object.AbstractEntity;
import it.batteringvalhalla.gamecore.object.Entity;
import it.batteringvalhalla.gamecore.object.actor.AbstractActor;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;
import it.batteringvalhalla.gamecore.vector2d.Vector2D;

public class CollisionHandler {
	private static List<Entity> objects = null;

	public static void setObjects(List<Entity> objects) {
		CollisionHandler.objects = new ArrayList<>(objects);
	}

	public static void check() {
		// check loop
		for (int i = 0; i < (objects.size() - 1); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				if (objects.get(i).getAlive() && objects.get(j).getAlive()) {
					// detect collision
					if (detect(objects.get(i), objects.get(j))) {
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

	private static void resolve(AbstractEntity first, AbstractEntity second) {
		if (!(first instanceof AbstractActor)) {
			return;
		}
		// TODO add sound effect
		// get the mtd vector
		Vector2D mtd = computeMtd(first, second);

		// old velocity
		Vector2D oldVel_first = new Vector2D();
		Vector2D oldVel_second = new Vector2D();
		Vector2D newVel_first = new Vector2D();
		Vector2D newVel_second = new Vector2D();
		AbstractActor a1 = (AbstractActor) first;

		if (second instanceof AbstractActor) {
			// fix overlap
			first.getOrigin().translate(mtd.getComponents().x, mtd.getComponents().y);
			// second.getOrigin().translate((1 + (mtd.getComponents().x / 2)) *
			// -1,(1 + (mtd.getComponents().y / 2)) * -1);

			AbstractActor a2 = (AbstractActor) second;
			// TODO actor->actor
			oldVel_first = new Vector2D(a1.getVelocity());
			oldVel_second = new Vector2D(a2.getVelocity());

			// check if mtd is along x-axis
			if (mtd.getComponents().x != 0) {
				newVel_first = new Vector2D((oldVel_first.getX() + (2 * oldVel_second.getX())),
						a1.getVelocity().getY());
				newVel_second = new Vector2D(oldVel_second.getX() + (2 * oldVel_first.getX()), a2.getVelocity().getY());
				a1.setVelocity(newVel_first);
				a2.setVelocity(newVel_second);
			}
			// check if mtd is along y-axis
			if (mtd.getComponents().y != 0) {
				newVel_first = new Vector2D(a1.getVelocity().getX(),
						(oldVel_first.getY() + (2 * oldVel_second.getY())));
				newVel_second = new Vector2D(a2.getVelocity().getX(), oldVel_second.getY() + (2 * oldVel_first.getY()));
				a1.setVelocity(newVel_first);
				a2.setVelocity(newVel_second);
			}

		} else if (second instanceof VerySquareWall) {
			// fix overlap
			first.getOrigin().translate(mtd.getComponents().x, mtd.getComponents().y);
			oldVel_first = new Vector2D(a1.getVelocity().getComponents().x, a1.getVelocity().getComponents().y);

			// check if mtd is along x-axis
			if (mtd.getComponents().x != 0) {
				a1.setVelocity(new Vector2D((oldVel_first.getComponents().x) * -1, a1.getVelocity().getComponents().y));

			}
			// check if mtd is along y-axis
			if (mtd.getComponents().y != 0) {
				a1.setVelocity(new Vector2D(a1.getVelocity().getComponents().x, (oldVel_first.getComponents().y) * -1));

			}
		}

	}

	public CollisionHandler() {
	}
}
