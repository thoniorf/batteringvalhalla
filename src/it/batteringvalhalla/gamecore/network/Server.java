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
		return status;
	}

	public void setStatus(ServerStatus status) {
		this.status = status;
	}

	public Server(ServerSocket socket) {
		this.socket = socket;
		status = ServerStatus.WAITING;
	}

	@Override
	public void run() {
		while (!ServerStatus.STOP.equals(status)) {
			if (ServerStatus.FULL.equals(status)) {

			}
		}
		while (ServerStatus.RUNNING.equals(status)) {

		}
		if (ServerStatus.EMPTY.equals(status)) {
			return;
		}
	}

	public boolean addClient(ServerDeamon deamon) {
		if (ServerStatus.FULL.equals(status)) {
			return false;
		}
		deamon.requestClientObjects();
		clients.add(deamon);
		new Thread(deamon).start();
		System.out.println(deamon.username + " connected");
		if (clients.size() == maxClients) {
			status = ServerStatus.FULL;
		}
		return true;
	}

	public void removeClient(ServerDeamon clientDeamon) {
		clients.remove(clientDeamon);
		if (clients.isEmpty()) {
			status = ServerStatus.EMPTY;
		}
	}

}
