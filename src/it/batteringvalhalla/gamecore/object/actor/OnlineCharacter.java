package it.batteringvalhalla.gamecore.object.actor;

import java.awt.Point;

public class OnlineCharacter extends AbstractActor {

	private static final long serialVersionUID = 3864857680898817354L;
	private String online_user;
	private int head;
	private int body;
	private int mount;

	public OnlineCharacter(String user, Point origin, int idHead, int idBody, int idMount) {
		super(origin, idHead, idBody, idMount);
		this.online_user = user;
		this.head = idHead;
		this.body = idBody;
		this.mount = idMount;
	}

	public OnlineCharacter(OnlineCharacter other) {
		super(other.origin, other.head, other.body, other.mount);
		this.online_user = other.online_user;
		this.head = other.head;
		this.body = other.body;
		this.mount = other.mount;
	}

	public String getOnline_user() {
		return online_user;
	}

	public void setOnline_user(String online_user) {
		this.online_user = online_user;
	}

	@Override
	public String toString() {
		return "OnlineCharacter [online_user=" + online_user + "]";
	}

}
