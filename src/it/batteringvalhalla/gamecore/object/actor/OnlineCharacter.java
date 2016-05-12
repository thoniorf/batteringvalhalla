package it.batteringvalhalla.gamecore.object.actor;

import it.batteringvalhalla.gamecore.State;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

public class OnlineCharacter extends AbstractActor implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7053714459623673342L;
	private static final int font_size = 8;
	private String online_user;
	private int headIndex;
	private int bodyIndex;
	private int mountIndex;
	private State state;

	public OnlineCharacter(String user, Point origin, int idHead, int idBody, int idMount) {
		super(origin, idHead, idBody, idMount);
		this.online_user = user;
		this.headIndex = idHead;
		this.bodyIndex = idBody;
		this.mountIndex = idMount;
	}

	public OnlineCharacter(OnlineCharacter other) {
		super(other.origin, other.headIndex, other.bodyIndex, other.mountIndex);
		this.online_user = other.online_user;
		this.headIndex = other.headIndex;
		this.bodyIndex = other.bodyIndex;
		this.mountIndex = other.mountIndex;
	}

	public String getOnline_user() {
		return this.online_user;
	}

	public void setOnline_user(String online_user) {
		this.online_user = online_user;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawString(this.online_user, this.shape.getBounds().x + (this.width / 2),
				(this.shape.getBounds().y + this.body.getOffsetY()) - font_size);
		super.paint(g);
	}

	@Override
	public String toString() {
		return this.online_user + " [" + headIndex + " " + bodyIndex + " " + mountIndex + "] " + super.toString();
	}

	// @Override
	// public boolean equals(Object obj) {
	// return ((OnlineCharacter) obj).online_user.equals(online_user);
	// }

}
