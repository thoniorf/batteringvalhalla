package it.batteringvalhalla.gamecore.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

	private BufferedReader br;
	private String name;
	private Socket socket;
	private PrintWriter print;
	private ServerGameManager server;

	public Client(final Socket s, final ServerGameManager server) {
		this.socket = s;
		this.server = server;
		this.name = "gatta1";

	}

	public void dispatch(final String message) {
		if (print != null && message != null) {
			print.println(message);
		}
	}

	public String getname() {
		return name;
	}

	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			print = new PrintWriter(socket.getOutputStream(), true);
			name = br.readLine();
			// server.dispatch(server.getClientConnected(), null);
			server.setReady(this);
			final boolean run = true;
			while (run) {
				final String buffer = br.readLine();
				// server.receive(buffer);
				server.dispatch(buffer, this);
				System.out.println(buffer + "client");
			}

		} catch (IOException e) {
			System.out.println("Client disconnected " + name);
		}

	}

	public String setUp() throws IOException {
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		print = new PrintWriter(socket.getOutputStream(), true);
		name = br.readLine();
		server.dispatch(server.getClientConnected(), null);
		return name;
	}

}
