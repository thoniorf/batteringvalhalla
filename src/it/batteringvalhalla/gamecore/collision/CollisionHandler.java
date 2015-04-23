package it.batteringvalhalla.gamecore.collision;

import it.batteringvalhalla.gamecore.object.AbstractGameObject;

import java.awt.Rectangle;
import java.util.ArrayList;

public class CollisionHandler {
	QuadTree quadtree;
	ArrayList<AbstractGameObject> returnsobjects;

	public CollisionHandler(Rectangle rect) {
		quadtree = new QuadTree(0, rect);
		returnsobjects = null;
	}

	public void checkCollisions(ArrayList<AbstractGameObject> objects) {
		quadtree.clear();
		for (int i = 0; i < objects.size(); i++) {
			quadtree.insert(objects.get(i));
		}
		returnsobjects = new ArrayList<AbstractGameObject>();
		for (int i = 0; i < objects.size(); i++) {
			returnsobjects.clear();
			quadtree.retrieve(returnsobjects, objects.get(i));

			for (int j1 = 0; j1 < returnsobjects.size(); j1++) {
				for (int j2 = 0; j2 < returnsobjects.size(); j2++) {
					if (objects.get(j1).getCollitionShape().intersecable()
							&& j1 != j2
							&& objects
									.get(j1)
									.getCollitionShape()
									.intersects(
											returnsobjects.get(j2)
													.getCollitionShape())) {
						objects.get(j1).getCollitionShape()
								.updateCollisionpoint();
						objects.get(j1).getCollitionShape().beginCollided();
						returnsobjects.get(j2).getCollitionShape()
								.updateCollisionpoint();
						returnsobjects.get(j2).getCollitionShape()
								.beginCollided();
					}
				}
			}

		}
	}
}
