package com.test.sept20.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricketTeam", "root", "password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}

}
