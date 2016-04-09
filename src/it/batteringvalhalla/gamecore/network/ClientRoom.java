package it.batteringvalhalla.gamecore.network;

import java.util.ArrayList;
import java.util.List;

import it.batteringvalhalla.gamecore.GameWorld;

public class ClientRoom {
	protected Integer maxClients;
	protected GameWorld sharedWorld;
	protected List<Object> clients;

	public ClientRoom(int maxClients) {
		clients = new ArrayList<>();
		this.maxClients = maxClients;
		this.sharedWorld = GameWorld.getWorld();
	}

	public void addClient(Object object) {
		if (maxClients > clients.size()) {
			clients.add(object);
		}
	}

	public Integer getMaxClients() {
		return maxClients;
	}

	public void setMaxClients(Integer maxClients) {
		this.maxClients = maxClients;
	}

	public void removeClient(Object owner) {
		clients.remove(owner);
	}

}
