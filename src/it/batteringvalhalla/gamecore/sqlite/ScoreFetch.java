package it.batteringvalhalla.gamecore.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ScoreFetch {

	Connection con;
	String dbpath = ScoreFetch.class.getProtectionDomain().getCodeSource().getLocation().getPath();

	public ScoreFetch() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void execQuery(String s, ArrayList<String> scores) {

		try {
			try {
				con = DriverManager
						.getConnection("jdbc:sqlite:" + dbpath + "it/batteringvalhalla/assets/db/" + "scores.db");
			} catch (SQLException e) {
				con = DriverManager
						.getConnection("jdbc:sqlite::resource:" + "it/batteringvalhalla/assets/db/" + "scores.db");

			}
			con.setAutoCommit(false);
			Statement statemnt = con.createStatement();
			ResultSet dSet = statemnt.executeQuery(s);
			while (dSet.next()) {
				scores.add(dSet.getString("user"));
				scores.add(dSet.getString("match"));
			}
			dSet.close();
			statemnt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertScore(Integer score, String p) {
		try {
			try {
				con = DriverManager
						.getConnection("jdbc:sqlite:" + dbpath + "it/batteringvalhalla/assets/db/" + "scores.db");
			} catch (SQLException e) {
				con = DriverManager
						.getConnection("jdbc:sqlite::resource:" + "it/batteringvalhalla/assets/db/" + "scores.db");

			}
			con.setAutoCommit(false);
			Statement statemnt = con.createStatement();
			statemnt.executeUpdate("Insert into scores values(null,'" + p + "'," + score.toString() + ");");
			statemnt.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
