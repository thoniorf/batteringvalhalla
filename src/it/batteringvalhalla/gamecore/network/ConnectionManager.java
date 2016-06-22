package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.ServerSocket;

import it.batteringvalhalla.gamegui.GameFrame;

public class ConnectionManager implements Runnable {
    public static int port = 46505;
    private static ServerSocket socket;

    public static ServerSocket getServerSocket() {
	return socket;
    }

    protected Server server;

    public ConnectionManager() {
	try {
	    socket = new ServerSocket(port);
	    this.server = new Server(socket);

	} catch (IOException e) {
	    System.err.println("Could not listen on port during inizialization" + port);
	    System.err.println("Maybe the port is busy");
	    Server.status = ServerStatus.STOP;
	    GameFrame.instance().showOnlineError("server_port_error");
	}
    }

    @Override
    public void run() {
	while (ServerStatus.WAITING.equals(Server.status) && server.clients.size() != Server.maxClients) {
	    try {
		// add & start new deamon
		ServerDeamon deamon = new ServerDeamon(socket.accept(), this.server, this.server.clients.size());
		server.addClient(deamon);
	    } catch (IOException e) {
		Server.status = ServerStatus.STOP;
		server.closeAll();
		System.err.println("Server stopped");
		return;
	    }
	}

	if (ServerStatus.WAITING.equals(Server.status)) {
	    new Thread(server).start();
	}
    }
}
