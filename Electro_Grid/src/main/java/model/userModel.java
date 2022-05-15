package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.Connect;

public class userModel {

	private String successful;
	
	public String getSuccessful() {
		return successful;
	}

	public void setSuccessful(String successful) {
		this.successful = successful;
	}

	public void addUsers(String name,String nic,String address,String email,String phone) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
			statement = connection.prepareStatement("insert into users (name, nic, address, email, phone) values (?,?,?,?,?)");
			statement.setString(1, name);
			statement.setString(2, nic);
			statement.setString(3, address);
			statement.setString(4, email);
			statement.setString(5, phone);
			statement.execute();
			statement.close();
			connection.close();
			setSuccessful("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setSuccessful("error");
		}
	}
	
	public String getUsers() {
		
		Connection connection;
		PreparedStatement statement;
		String data="";
		
		try {
			
			connection = Connect.getConnection();
			statement = connection.prepareStatement("SELECT * FROM users");
			
			ResultSet resultSet = statement.executeQuery();
			
			data = "<table style='border: 1px solid black;border-radius: 10px;'><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Name</th>"
	                +"<th style='border: 1px solid black;'>NIC</th>"
	                +"<th style='border: 1px solid black;'>Address</th>"
	                +"<th style='border: 1px solid black;'>Email</th>"
	                +"<th style='border: 1px solid black;'>Phone</th>"
	                +"<th style='border: 1px solid black;'>Action</th>"
	                +"</tr>"
	            +"</thead><tbody>";
			
			while (resultSet.next()) {
				
				data = data+"<tr><td style='border: 1px solid black;'>"+resultSet.getString(1)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(4)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(5)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(6)+"</td>"
						+ "<td style='border: 1px solid black;'><button type='button' onclick=''>Delete</button></td>"
					  + "</tr>";
				
			}
			
			statement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
		}
		
		return data+"</table>";
	}

	public void editUsers(int id,String name,String nic,String address,String email,String phone) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
				statement = connection.prepareStatement("UPDATE users SET name=?, nic=?, address=?, email=?, phone=? where id=?");
				statement.setString(1, name);
				statement.setString(2, nic);
				statement.setString(3, address);
				statement.setString(4, email);
				statement.setString(5, phone);
				statement.setInt(6,id);
				statement.execute();
				statement.close();
				connection.close();
				setSuccessful("success");
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setSuccessful("error");
		}
	}

	public void deleteUsers(int id) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
			statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
			statement.setInt(1, id);
			statement.execute();
			
			setSuccessful("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccessful("error");
		}
	}
	
}
