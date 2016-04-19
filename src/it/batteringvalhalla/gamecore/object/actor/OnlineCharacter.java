package it.batteringvalhalla.gamecore.object.actor;

import java.awt.Point;

public class OnlineCharacter extends AbstractActor {

	private String online_user;

	public OnlineCharacter(String user, Point origin, int idHead, int idBody, int idMount) {
		super(origin, idHead, idBody, idMount);
		this.online_user = user;
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
