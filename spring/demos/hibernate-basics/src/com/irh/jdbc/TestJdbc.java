package com.irh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "student";
		String pass = "studentpwd";
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			@SuppressWarnings("unused")
			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
