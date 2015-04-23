package it.batteringvalhalla.gamecore.collision;

import it.batteringvalhalla.gamecore.object.AbstractGameObject;

import java.awt.Rectangle;
import java.util.ArrayList;

public class QuadTree {

	private final Integer MAX_OBJECTS = 10;
	private final Integer MAX_LEVELS = 5;

	private Integer level;
	private ArrayList<AbstractGameObject> objects;
	private Rectangle bounds;
	private ArrayList<QuadTree> nodes;

	public QuadTree(Integer level, Rectangle bounds) {
		this.level = level;
		this.bounds = bounds;
		objects = new ArrayList<AbstractGameObject>();
		this.nodes = new ArrayList<QuadTree>(4);
		this.nodes.add(null);
		this.nodes.add(null);
		this.nodes.add(null);
		this.nodes.add(null);

	}

	public void clear() {
		this.objects.clear();

		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i) != null) {
				nodes.get(i).clear();
				nodes.set(i, null);
			}
		}
	}

	private void split() {
		Integer subWidth = (int) (this.bounds.getWidth() / 2);
		Integer subHeight = (int) (this.bounds.getHeight() / 2);
		Integer x = (int) (this.bounds.getX());
		Integer y = (int) (this.bounds.getY());
		nodes.set(0, new QuadTree(level + 1, new Rectangle(x + subWidth, y,
				subWidth, subHeight)));
		nodes.set(1, new QuadTree(level + 1, new Rectangle(x, y, subWidth,
				subHeight)));
		nodes.set(2, new QuadTree(level + 1, new Rectangle(x, y + subHeight,
				subWidth, subHeight)));
		nodes.set(3, new QuadTree(level + 1, new Rectangle(x + subWidth, y
				+ subHeight, subWidth, subHeight)));
	}

	private Integer getIndex(AbstractGameObject obj) {
		Integer index = -1;

		double verticalmidpoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalmidpoint = bounds.getY() + (bounds.getHeight() / 2);

		boolean topquadrant = (obj.getY() < horizontalmidpoint && obj.getY()
				+ obj.getHeight() < horizontalmidpoint);
		boolean bottomquadrant = (obj.getX() < verticalmidpoint && obj.getX()
				+ obj.getWidth() < verticalmidpoint);
		if (topquadrant) {
			index = 1;
		} else if (bottomquadrant) {
			index = 2;
		} else if (obj.getX() > verticalmidpoint) {
			if (topquadrant) {
				index = 0;
			} else if (bottomquadrant) {
				index = 3;
			}
		}
		return index;
	}

	public void insert(AbstractGameObject object) {
		if (nodes.get(0) != null) {
			Integer index = getIndex(object);

			if (index != -1) {
				nodes.get(index).insert(object);

				return;
			}
		}

		objects.add(object);

		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes.get(0) == null) {
				split();
			}

			Integer counter = 0;
			while (counter < objects.size()) {
				Integer index = getIndex(objects.get(counter));
				if (index != -1) {
					nodes.get(index).insert(objects.get(counter));
					objects.remove(counter);
				} else {
					counter += 1;
				}
			}
		}
	}

	public ArrayList<AbstractGameObject> retrieve(
			ArrayList<AbstractGameObject> returnshapes, AbstractGameObject shape) {
		Integer index = getIndex(shape);
		if (index != -1 && nodes.get(0) != null) {
			nodes.get(index).retrieve(returnshapes, shape);
		}

		returnshapes.addAll(objects);
		return returnshapes;
	}
}
