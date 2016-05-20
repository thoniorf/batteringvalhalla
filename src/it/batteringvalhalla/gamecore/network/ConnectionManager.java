package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.ServerSocket;

import it.batteringvalhalla.gamegui.GameFrame;

public class ConnectionManager implements Runnable {
	public static int port = 46505;
	private ServerSocket socket;
	protected Server server;

	public ConnectionManager() {
		try {
			this.socket = new ServerSocket(port);
			this.server = new Server(this.socket);

		} catch (IOException e) {
			System.err.println("Could not listen on port " + port);
			System.err.println("Maybe the port is busy");
			Server.status = ServerStatus.STOP;
			GameFrame.instance().showOnlineError("server_port_error");
		}
	}

	@Override
	public void run() {
		while (!ServerStatus.STOP.equals(Server.status) && !(server.clients.size() == Server.maxClients)) {
			try {
				// add & start new deamon
				ServerDeamon deamon = new ServerDeamon(this.socket.accept(), this.server, this.server.clients.size());
				server.addClient(deamon);
			} catch (IOException e) {
				System.err.println("Could not listen on port " + port + ". Maybe the port is busy");
				Server.status = ServerStatus.STOP;
				GameFrame.instance().showOnlineError("server_port_error");
				return;
			}
		}
		if (!ServerStatus.STOP.equals(Server.status)) {
			new Thread(server).start();
		}
	}
}
