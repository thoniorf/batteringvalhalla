package it.batteringvalhalla.gamegui.sound;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSound {

	public FileSound() {

		construct();

	}

	public void write0() {
		FileWriter w;
		try {

			w = new FileWriter("options.txt");
			BufferedWriter bw = new BufferedWriter(w);

			bw.write("0");
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write1() {
		FileWriter w;
		try {

			w = new FileWriter("options.txt");
			BufferedWriter bw = new BufferedWriter(w);
			bw.write("1");
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() {
		FileReader r;

		String s;

		String s1 = "0";
		String s2 = "1";

		try {

			r = new FileReader("options.txt");

			BufferedReader br = new BufferedReader(r);

			s = br.readLine();
			br.close();

			if (s.equals(s1)) {
				return s1;

			} else {
				return s2;

			}

		} catch (IOException e) {

			e.printStackTrace();

		}
		return "";

	}

	void construct() {
		if (read().equals("")) {
			write0();
		} else if (read().equals("1")) {
			write1();
		} else if (read().equals("0")) {
			write0();
		}

	}

}
