package it.batteringvalhalla.gamecore.object.actor;

import java.io.Serializable;

import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamecore.vector2d.Vector2D;

public interface Actor extends Serializable {

	public Vector2D getVelocity();

	public void setVelocity(Vector2D velocity);

	public Direction getFaceDirection();

	public void setFaceDirection(Direction face_dir);

	public Direction getMoveDirection();

	public void setMoveDirection(Direction face_dir);
}
