package it.batteringvalhalla.gamecore.network;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;
import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamecore.vector2d.Vector2D;

public class CharacterMessage implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6199190989912637907L;
	protected String username;
	protected Boolean alive;
	protected Point origin;

	protected int curret_max_velocity;

	protected Vector2D velocity;
	protected Direction face_dir;
	protected Direction move_dir;
	protected Boolean can_charge;
	protected Boolean charge;
	protected long charge_time;

	public CharacterMessage(OnlineCharacter character) {
		username = new String(character.getOnline_user());
		alive = character.getAlive();
		origin = new Point(character.getOrigin());
		curret_max_velocity = character.getMaxVelocity();
		velocity = new Vector2D(character.getVelocity());
		face_dir = character.getFaceDirection();
		move_dir = character.getMoveDirection();
		can_charge = character.canCharge();
		charge = character.getCharge();
		charge_time = new Long(character.getCharge_time());
	}

	public boolean syncCharacter(OnlineCharacter character) {
		if (character.getOnline_user().equals(username)) {
			character.setAlive(alive);
			character.setOrigin(origin);
			character.setCurretMaxVelocity(curret_max_velocity);
			character.setVelocity(velocity);
			character.setFaceDirection(face_dir);
			character.setMoveDirection(move_dir);
			character.setCharge(charge);
			character.setCharge_time(charge_time);
			return true;
		}
		return false;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUnshared(username);
		out.writeUnshared(alive);
		out.writeUnshared(origin);
		out.writeUnshared(curret_max_velocity);
		out.writeUnshared(velocity);
		out.writeUnshared(face_dir);
		out.writeUnshared(move_dir);
		out.writeUnshared(can_charge);
		out.writeUnshared(charge);
		out.writeUnshared(charge_time);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		username = new String((String) in.readUnshared());
		alive = (Boolean) in.readUnshared();
		origin = new Point((Point) in.readUnshared());
		curret_max_velocity = (int) in.readUnshared();
		velocity = new Vector2D((Vector2D) in.readUnshared());
		face_dir = (Direction) in.readUnshared();
		move_dir = (Direction) in.readUnshared();
		can_charge = (Boolean) in.readUnshared();
		charge = (Boolean) in.readUnshared();
		charge_time = new Long((long) in.readUnshared());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public int getCurret_max_velocity() {
		return curret_max_velocity;
	}

	public void setCurret_max_velocity(int curret_max_velocity) {
		this.curret_max_velocity = curret_max_velocity;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public Direction getFace_dir() {
		return face_dir;
	}

	public void setFace_dir(Direction face_dir) {
		this.face_dir = face_dir;
	}

	public Direction getMove_dir() {
		return move_dir;
	}

	public void setMove_dir(Direction move_dir) {
		this.move_dir = move_dir;
	}

	public Boolean getCan_charge() {
		return can_charge;
	}

	public void setCan_charge(Boolean can_charge) {
		this.can_charge = can_charge;
	}

	public Boolean getCharge() {
		return charge;
	}

	public void setCharge(Boolean charge) {
		this.charge = charge;
	}

	public long getCharge_time() {
		return charge_time;
	}

	public void setCharge_time(long charge_time) {
		this.charge_time = charge_time;
	}

	@Override
	public String toString() {
		return "CharacterMessage [username=" + username + ", alive=" + alive + ", origin=" + origin
				+ ", curret_max_velocity=" + curret_max_velocity + ", velocity=" + velocity + ", face_dir=" + face_dir
				+ ", move_dir=" + move_dir + ", can_charge=" + can_charge + ", charge=" + charge + ", charge_time="
				+ charge_time + "]";
	}

}
