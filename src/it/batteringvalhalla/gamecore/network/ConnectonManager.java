package it.batteringvalhalla.gamecore.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectonManager implements Runnable {

	Socket socket;
	PrintWriter pw;

	// GameWorld gw;

	public ConnectonManager(Socket s /* GameWorld g */) {
		this.socket = s;
		// this.gw = g;
	}

	@Override
	public void run() {
		try {
			final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);
			// gw = gw.startNetwork(this);
			String buffer = br.readLine();

			while (buffer != null) {
				// System.out.println("conn");
				// System.out.println(buffer + "connection");
				if (!buffer.equals("#START")) {
					String[] split = buffer.split(":");
					// gw.getB().setDirection(Integer.parseInt(split[0]));

					// gw.repaint();

				}
				buffer = br.readLine();
				// gw.update();
				// gw.repaint();

			}

		} catch (IOException e) {

			System.out.println("connessione chiusa");
		}

	}

	public void dispatch(String buffer) {

		pw.println(buffer);

	}

}
