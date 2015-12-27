package it.batteringvalhalla.gamecore.network;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			String messaggio;
			String s = "";
			Socket socket = null;

			System.out.println("ok");
			socket = new Socket("127.0.0.1", 8183);

			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

			DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (!s.equals("BY")) {
				s = inFromUser.readLine();
				if (outToServer != null && !s.equals(" ")) {
					outToServer.writeBytes(s + '\n');

				}
				messaggio = inFromServer.readLine();
				System.out.println(messaggio + " gatta");
				System.out.println("FROM " + socket.getInetAddress() + messaggio);

			}
			socket.close();

		} catch (IOException e) {
			System.out.println("conversazione interrotta");
			e.printStackTrace();
		}

	}
}
