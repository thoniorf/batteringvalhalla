package it.batteringvalhalla.gamecore.network;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static void main(String[] args) {
		ServerSocket serverS;
		try {

			ArrayList<Socket> s = new ArrayList<Socket>();
			serverS = new ServerSocket(8183);
			System.out.println("waiting..");

			while (true) {

				Socket socket = serverS.accept();
				s.add(socket);
				System.out.println("accettato " + socket.getInetAddress());
				Thread t = new Thread(new ClientWorker(s, socket));

				t.start();

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
