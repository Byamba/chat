package com.seventysevenagency.chat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException("Cannot find driver class", e1);
		} 
		String url = "jdbc:postgresql://localhost:5432/chat";
		String username = "devel";
		String password = "banzaj";
		Connection result;
		try {
			result = DriverManager
					.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException("Cannot get connection", e);
		}
		return result;
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Cannot close connection", e);
		}
	}
}
