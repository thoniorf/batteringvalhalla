package it.batteringvalhalla;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.network.CharacterMessage;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;

public class DeserializeServer {

	public DeserializeServer() {

	}

	public static void main(String[] args) {
		try {
			ResourcesLoader.loadPlayerImages();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ServerSocket server = null;
		Socket socket = null;
		OnlineCharacter player = null;
		try {
			server = new ServerSocket(8020);
			socket = server.accept();
			ObjectInputStream i = new ObjectInputStream(socket.getInputStream());

			player = (OnlineCharacter) i.readUnshared();
			System.out.println(player);
			player = (OnlineCharacter) i.readUnshared();
			System.out.println(player);
			CharacterMessage message = (CharacterMessage) i.readUnshared();
			if (message.syncCharacter(player)) {
				System.out.println(player);
			}
			System.out.println(message);

			i.close();
			socket.close();
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
