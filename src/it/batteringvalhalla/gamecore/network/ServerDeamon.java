package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.object.actor.OnlineCharacter;
import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamecore.object.wall.VerySquareWall;

public class ServerDeamon implements Runnable {
    protected int id;
    protected Socket socket;
    protected Server server;
    protected NetworkProtocol protocol;
    protected OnlineCharacter client;

    public ServerDeamon(Socket socket, Server server, int index) {
	this.socket = socket;
	this.server = server;
	this.id = index;
	try {
	    this.protocol = new NetworkProtocol(socket.getInputStream(), socket.getOutputStream());
	} catch (IOException e) {
	    System.err.println("Error with client connection I/O during initialization ");
	    e.printStackTrace();
	}
    }

    public Object request() {
	try {
	    return this.protocol.request();
	} catch (ClassNotFoundException e) {
	    System.err.println("Class not found during sync in server");
	    protocol.close(socket);
	    this.server.removeClient(this);
	} catch (IOException e) {
	    System.err.println("Error with client I/O during sync");
	    protocol.close(socket);
	    this.server.removeClient(this);
	}
	return null;
    }

    @Override
    public void run() {
	try {
	    this.protocol.send(true);
	} catch (IOException e) {
	    System.err.println("Error with client connection I/O with start flag");
	    protocol.close(socket);
	}
	while (!socket.isClosed() && !ServerStatus.STOP.equals(Server.status)) {
	    try {
		client.setMoveDirection((Direction) this.protocol.request());
	    } catch (ClassNotFoundException e) {
		System.err.println("Class not found on update");
		protocol.close(socket);
		break;
	    } catch (IOException e) {
		System.err.println(
			"Error with client connection I/O on update // si e' disconnesso " + client.getOnline_user());
		protocol.close(socket);
		client.setAlive(false);
		break;
	    }
	}
	client.setAlive(false);
	this.server.removeClient(this);

    }

    public void send(Object obj) {
	try {
	    this.protocol.send(obj);
	} catch (SocketTimeoutException e) {
	    System.err.println("Timeout during sync");
	    protocol.close(socket);
	    this.server.removeClient(this);
	} catch (IOException e) {
	    System.err.println("Error with client I/O during sync");
	    protocol.close(socket);
	    this.server.removeClient(this);
	}
    }

    public void syncClient() {
	// send arena
	this.send(GameWorld.getArena());
	// send walls
	this.send(GameWorld.getWalls().size());
	for (VerySquareWall wall : GameWorld.getWalls()) {
	    this.send(wall);
	}
	// send character
	send(server.clients.size());
	client = (OnlineCharacter) this.request();
	send(Server.maxClients);

    }
}
