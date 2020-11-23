package com.myproject.conn;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConn {

	public static void main(String[] args) {
		try {
			Connection conn = DbConn.getInstance().getConnection();
			System.out.println("Connesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
