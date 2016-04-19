package it.batteringvalhalla.gamecore.network;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {
	protected int maxClients = 4;
	protected ServerSocket socket;
	protected ServerStatus status;
	protected List<ServerDeamon> clients = new ArrayList<>();

	public ServerStatus getStatus() {
		return this.status;
	}

	public void setStatus(ServerStatus status) {
		this.status = status;
	}

	public Server(ServerSocket socket) {
		this.socket = socket;
		this.status = ServerStatus.WAITING;
	}

	@Override
	public void run() {
		while (!ServerStatus.STOP.equals(this.status)) {
			if (ServerStatus.FULL.equals(this.status)) {

			}
		}

		if (ServerStatus.EMPTY.equals(this.status)) {
			return;
		}
	}

	public boolean addClient(ServerDeamon deamon) {
		if (!ServerStatus.FULL.equals(this.status)) {
			// sync client object
			deamon.syncClient();
			// add to clients lists
			this.clients.add(deamon);
			// set id for client
			deamon.id = this.clients.size();
			if (this.clients.size() == this.maxClients) {
				this.status = ServerStatus.FULL;
				syncAll();
			}
			return true;
		}

		return false;
	}

	public void removeClient(ServerDeamon clientDeamon) {
		this.clients.remove(clientDeamon);
		if (this.clients.isEmpty()) {
			this.status = ServerStatus.EMPTY;
		}
	}

	public void syncAll() {
		for (ServerDeamon deamon : this.clients) {
			for (ServerDeamon minion : this.clients) {
				if (deamon.id != minion.id) {
					deamon.sendOther(minion);
				}
			}
		}
	}

}
