package it.batteringvalhalla.gamecore.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

public class NetworkProtocol {

	protected ObjectInputStream in;
	protected ObjectOutputStream out;

	public NetworkProtocol(InputStream in, OutputStream out) throws IOException {
		this.out = new ObjectOutputStream(out);
		this.out.flush();
		this.in = new ObjectInputStream(in);

	}

	public void send(Object obj) throws IOException {
		this.out.writeObject(obj);
		this.out.flush();
	}

	public void send(List<?> objs) throws IOException {
		this.out.writeObject(objs.size());
		this.out.flush();
		for (Object object : objs) {
			this.out.writeObject(object);
			this.out.flush();
		}
	}

	public Object request() throws ClassNotFoundException, IOException {
		return this.in.readObject();
	}

	public void close() throws IOException {
		this.out.close();
		this.in.close();
	}
}
