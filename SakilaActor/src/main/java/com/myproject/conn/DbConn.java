package com.myproject.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConn {
	private static DbConn instance;

	private String url = "jdbc:postgresql://localhost:5432/dvdrental";
	private String username = "postgres";
	private String password = "prova";
	private Connection conn;

	private Statement stm;

	private DbConn() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");

			this.conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException ex) {
			System.out.println("DB connesso : " + ex.getMessage());
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public static DbConn getInstance() throws SQLException {
		if (instance == null) {
			instance = new DbConn();
		} else if (instance.getConnection().isClosed()) {
			instance = new DbConn();
		}

		return instance;
	}

}
