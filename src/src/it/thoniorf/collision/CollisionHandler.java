package it.thoniorf.collision;

import it.thoniorf.entity.GameObject;

import java.awt.Rectangle;
import java.util.ArrayList;

public class CollisionHandler {
	QuadTree quadtree;
	ArrayList<GameObject> returnsobjects;

	public CollisionHandler(Rectangle rect) {
		quadtree = new QuadTree(0, rect);
		returnsobjects = null;
	}

	public void checkCollisions(ArrayList<GameObject> objects) {
		quadtree.clear();
		for (int i = 0; i < objects.size(); i++) {
			quadtree.insert(objects.get(i));
		}
		returnsobjects = new ArrayList<GameObject>();
		for (int i = 0; i < objects.size(); i++) {
			returnsobjects.clear();
			quadtree.retrieve(returnsobjects, objects.get(i));

			for (int j1 = 0; j1 < returnsobjects.size(); j1++) {
				for (int j2 = 0; j2 < returnsobjects.size(); j2++) {
					if (objects.get(j1).getCollisionShape().intersecable()
							&& j1 != j2
							&& objects
									.get(j1)
									.getCollisionShape()
									.intersects(
											returnsobjects.get(j2)
													.getCollisionShape())) {
						objects.get(j1).getCollisionShape()
								.updateCollisionpoint();
						objects.get(j1).getCollisionShape().beginCollided();
						returnsobjects.get(j2).getCollisionShape()
								.updateCollisionpoint();
						returnsobjects.get(j2).getCollisionShape()
								.beginCollided();
					}
				}
			}

		}
	}
}
