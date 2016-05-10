package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.ServerSocket;

public class ConnectionManager implements Runnable {
	private static final int port = 46505;
	private ServerSocket socket;
	protected Server server;

	public ConnectionManager() {
		try {
			this.socket = new ServerSocket(port);
			this.server = new Server(this.socket);
		} catch (IOException e) {
			System.err.println("Could not listen on port " + port);
			System.err.println("Maybe the port is busy");
			// TODO Add erro menu
		}
	}

	@Override
	public void run() {

		while (!(server.clients.size() == server.maxClients)) {
			try {
				// add & start new deamon
				ServerDeamon deamon = new ServerDeamon(this.socket.accept(), this.server, this.server.clients.size());
				this.server.addClient(deamon);
			} catch (IOException e) {
				System.err.println("Could not listen on port " + port + ". Maybe the port is busy");
				// TODO Add error menu
				return;
			}
		}
		new Thread(server).start();

	}
}
