package it.batteringvalhalla.gamecore.object.actor;

import java.awt.Graphics2D;
import java.awt.Point;

public class OnlineCharacter extends AbstractActor {

	private static final long serialVersionUID = 3864857680898817354L;
	private static final int font_size = 8;
	private String online_user;
	private int headIndex;
	private int bodyIndex;
	private int mountIndex;

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

	@Override
	public void paint(Graphics2D g) {

		g.drawString(this.online_user, this.shape.getBounds().x + (this.width / 2),
				(this.shape.getBounds().y + this.body.getOffsetY()) - font_size);
		super.paint(g);
	}

	@Override
	public String toString() {
		return "OnlineCharacter [online_user=" + this.online_user + "]";
	}

}
