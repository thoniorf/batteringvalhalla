package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ServerGameManager {

	private final Set<Client> clients = new HashSet<Client>();

	private final Set<Client> readyClients = new HashSet<Client>();

	ConnectonManager c;

	public void add(final Client c) {
		clients.add(c);
	}

	public Set<Client> getClients() {
		return clients;
	}

	public String getClientConnected() {

		final StringBuilder sb = new StringBuilder();
		for (final Client cm : clients) {
			if (cm.getname() != null) {
				sb.append(cm.getname());
				sb.append(";");
			}
		}
		return sb.toString();

	}

	public void dispatch(final String message, Client senderClient) {
		for (final Client client : clients) {
			if (client != senderClient) {
				client.dispatch(message);
			}
		}

	}

	public void setReady(final Client client) {
		synchronized (readyClients) {
			readyClients.add(client);
			if (readyClients.size() == 2) {
				dispatch("#START", null);
				System.out.println("Go serverGameManager");
			}
		}

	}

	public void run() throws IOException {

		for (final Client client : clients) {
			// client.setUp();
			new Thread(client, client.toString()).start();
			System.out.println("run");

		}
		// gw = new GameWorld();
		// gw.start();

	}

	public void receive(String buffer) {
		String[] split = buffer.split(":");
		// gw.getB().setDirection(Integer.parseInt(split[0]));
		// System.out.println(Integer.parseInt(split[0]) + "direction");
		// System.out.println(gw.getB().getDirection() + "controllo");

	}

}
