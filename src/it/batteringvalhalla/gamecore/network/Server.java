package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	final int port = 8183;
	private ServerSocket serverSocket;

	public void runServer() throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("waiting");
		while (true) {

			final ServerGameManager server = new ServerGameManager();
			final Socket s = serverSocket.accept();
			final Client client = new Client(s, server);
			server.add(client);
			System.out.println("connesso 1");
			final Socket s1 = serverSocket.accept();
			final Client client2 = new Client(s1, server);
			server.add(client2);
			System.out.println("connesso 2");
			server.run();
			System.out.println("iniziamo");
		}
	}

	public static void main(String[] args) throws IOException {

		Server server = new Server();
		server.runServer();

	}

}
