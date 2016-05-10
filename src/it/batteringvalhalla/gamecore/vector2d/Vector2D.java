package it.batteringvalhalla.gamecore.vector2d;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Vector2D implements Serializable {

	private static final long serialVersionUID = -6491072769000654057L;
	protected Point components;
	protected Float lenght;

	public Vector2D() {
		this.lenght = 0f;
		this.components = new Point(0, 0);
	}

	public Vector2D(Integer x, Integer y) {
		this.components = new Point(x, y);
		this.lenght = getLenght();
	}

	public Vector2D(Point origin) {
		this.components = origin;
		this.lenght = getLenght();
	}

	public Vector2D(Vector2D vector) {
		this.lenght = vector.lenght;
		this.components = vector.components;
	}

	public Point getComponents() {
		return components;
	}

	public Float getLenght() {
		return (float) Math.sqrt(components.x ^ 2 + components.y ^ 2);
	}

	public Vector2D normalize() {
		return new Vector2D((int) (components.x / Math.abs(lenght)), (int) (components.y / Math.abs(lenght)));
	}

	public void sum(Vector2D vector) {
		this.components.x += vector.getComponents().x;
		this.components.y += vector.getComponents().y;
	}

	public void subtract(Vector2D vector) {
		this.components.x = this.components.x - vector.getComponents().x;
		this.components.y = this.components.y - vector.getComponents().y;
	}

	public void divide(Integer scalar) {
		this.components.x /= scalar;
		this.components.y /= scalar;
	}

	public Integer dotProduct(Vector2D vector) {
		return this.components.x * vector.getComponents().x + this.components.y * vector.getComponents().y;
	}

	public void setComponents(int x, int y) {
		components.x = x;
		components.y = y;
	}

	public void setComponents(Point origin) {
		this.components = origin;
	}

	public int getX() {
		return this.components.x;
	}

	public int getY() {
		return this.components.y;
	}

	public void setX(Integer x) {
		this.components.x = x;
	}

	public void setY(Integer y) {
		this.components.y = y;
	}

	@Override
	public String toString() {
		return "[" + lenght + "]" + components.x + " " + components.y;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUnshared(components);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		components = (Point) in.readUnshared();
		lenght = getLenght();
	}
}
