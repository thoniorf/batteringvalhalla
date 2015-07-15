package it.batteringvalhalla.gamecore.collision;

import it.batteringvalhalla.gamecore.object.AbstractGameObject;
import it.batteringvalhalla.gamecore.object.actor.Actor;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
	QuadTree quadtree;
	ArrayList<AbstractGameObject> returnsobjects;

	public CollisionHandler(Rectangle rect) {
		quadtree = new QuadTree(0, rect);
		returnsobjects = new ArrayList<AbstractGameObject>();
	}

	public void checkCollisions(List<AbstractGameObject> arrayList) {
		/*
		 * quadtree.clear(); for (int i = 0; i < objects.size(); i++) {
		 * quadtree.insert(objects.get(i)); }
		 * 
		 * for (int i = 0; i < objects.size(); i++) { returnsobjects.clear();
		 * quadtree.retrieve(returnsobjects, objects.get(i)); for (int j = 0; j
		 * < returnsobjects.size(); j++) { if
		 * (objects.get(i).getCollisionShape().intersecable() &&
		 * returnsobjects.get(i).getCollisionShape() .intersecable() && i != j
		 * && objects .get(i) .getCollisionShape() .intersects(
		 * returnsobjects.get(j) .getCollisionShape())) {
		 * 
		 * objects.get(i).getCollisionShape().updateCollisionpoint();
		 * objects.get(i).postCollision(returnsobjects.get(j));
		 * 
		 * } } }
		 */

		for (int i = 0; i < arrayList.size(); i++) {
			for (int j = 0; j < arrayList.size(); j++) {
				if (i != j
						&& ((Actor) arrayList.get(i)).getLive() != 0
						&& ((Actor) arrayList.get(j)).getLive() != 0
						&& arrayList.get(i).getCollisionShape().intersecable()
						&& arrayList.get(j).getCollisionShape().intersecable()
						&& arrayList
								.get(i)
								.getCollisionShape()
								.intersects(
										arrayList.get(j).getCollisionShape())) {

					arrayList.get(i).getCollisionShape().updateCollisionpoint();
					// arrayList.get(j).getCollisionShape().updateCollisionpoint();
					arrayList.get(i).postCollision((Actor) arrayList.get(j));
					// arrayList.get(j).postCollision((Actor) arrayList.get(i));
				}
			}
		}
	}
}
