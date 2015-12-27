package it.batteringvalhalla.gamecore.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientWorker extends Thread {

	ArrayList<Socket> sockets;
	Socket socket;
	String messaggio = "";
	String m = "";

	public ClientWorker(ArrayList<Socket> s, Socket si) {
		this.sockets = s;
		this.socket = si;

	}

	@Override
	public void run() {
		try {

			while (!m.equals("BY")) {

				for (int j = 0; j < sockets.size(); j++) {

					BufferedReader inFromClient = new BufferedReader(new InputStreamReader(sockets.get(j).getInputStream()));

					if (socket != sockets.get(j)) {

						PrintWriter outToClient = new PrintWriter(socket.getOutputStream());
						m = inFromClient.readLine();
						if (inFromClient != null && !m.equals(" ")) {
							outToClient.println(m + '\n');
							outToClient.flush();
						}
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
