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
			socket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Could not listen on port " + port);
			System.err.println("Maybe the port is busy");
			// Game Restart
			GameFrame.instance().restart();
		}
		server = new Server(socket);
		new Thread(server).start();
	}

	@Override
	public void run() {

		while (!ServerStatus.FULL.equals(server.getStatus())) {
			try {
				// add & start new deamon
				ServerDeamon deamon = new ServerDeamon(socket.accept(), server);
				server.addClient(deamon);
			} catch (IOException e) {
				System.err.println("Could not listen on port " + port + ". Maybe the port is busy");
				// Game Restart
				GameFrame.instance().restart();
				return;
			}
		}

	}
}
