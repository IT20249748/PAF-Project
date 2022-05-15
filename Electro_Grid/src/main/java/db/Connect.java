package db;

import java.sql.*;

public class Connect {
	
	private static Connection conn;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		if (conn == null || conn.isClosed()) {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Electro_Grid", "root", "");
		}
		
		return conn;
	}

}
