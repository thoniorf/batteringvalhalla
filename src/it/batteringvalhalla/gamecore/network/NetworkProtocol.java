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
		out.writeObject(obj);
		out.flush();
	}

	public void send(List<?> objs) throws IOException {
		out.writeObject(objs.size());
		out.flush();
		for (Object object : objs) {
			out.writeObject(object);
			out.flush();
		}
	}

	public Object request() throws ClassNotFoundException, IOException {
		return in.readObject();
	}

	public void close() throws IOException {
		out.close();
		in.close();
	}

	public boolean isAvaible() {
		try {
			in.readByte();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
