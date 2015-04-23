package it.batteringvalhalla.gamecore.collision;

import it.batteringvalhalla.gamecore.object.AbstractGameObject;

import java.awt.Rectangle;
import java.util.ArrayList;

public class CollisionHandler {
	QuadTree quadtree;
	ArrayList<AbstractGameObject> returnsobjects;

	public CollisionHandler(Rectangle rect) {
		quadtree = new QuadTree(0, rect);
		returnsobjects = new ArrayList<AbstractGameObject>();
	}

	public void checkCollisions(ArrayList<AbstractGameObject> objects) {
		quadtree.clear();
		for (int i = 0; i < objects.size(); i++) {
			quadtree.insert(objects.get(i));
		}

		for (int i = 0; i < objects.size(); i++) {
			returnsobjects.clear();
			quadtree.retrieve(returnsobjects, objects.get(i));
			for (int j = 0; j < returnsobjects.size(); j++) {
				if (objects.get(i).getCollisionShape().intersecable()
						&& i != j
						&& objects
								.get(i)
								.getCollisionShape()
								.intersects(
										returnsobjects.get(j)
												.getCollisionShape())) {
					returnsobjects.get(j).getCollisionShape()
							.updateCollisionpoint();
					returnsobjects.get(j).postCollision(objects.get(i));
					objects.get(i).getCollisionShape().updateCollisionpoint();
					objects.get(i).postCollision(returnsobjects.get(j));
				}
			}
		}
	}
}
