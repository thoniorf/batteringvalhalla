package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.ServerSocket;

import it.batteringvalhalla.gamegui.GameFrame;

public class ConnectionManager implements Runnable {
	private static final int port = 46505;
	private ServerSocket socket;
	protected Server server;

	public ConnectionManager() {
		try {
			this.socket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Could not listen on port " + port);
			System.err.println("Maybe the port is busy");
			// Game Restart
			GameFrame.instance().restart();
		}
		this.server = new Server(this.socket);
	}

	@Override
	public void run() {

		while (ServerStatus.WAITING.equals(this.server.getStatus())) {
			try {
				// add & start new deamon
				ServerDeamon deamon = new ServerDeamon(this.socket.accept(), this.server);
				this.server.addClient(deamon);
			} catch (IOException e) {
				System.err.println("Could not listen on port " + port + ". Maybe the port is busy");
				// Game Restart
				GameFrame.instance().restart();
				return;
			}
		}

	}
}
