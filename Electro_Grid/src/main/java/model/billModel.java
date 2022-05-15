package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.Connect;

public class billModel {
	
	private String successful;
	
	public String getSuccessful() {
		return successful;
	}

	public void setSuccessful(String successful) {
		this.successful = successful;
	}

	public void addBill(int user_id,int unit_usage,String date,double unit_price,double total) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
			statement = connection.prepareStatement("insert into bill (user_id, unit_usage, date, unit_price, total) values (?,?,?,?,?)");
			statement.setInt(1, user_id);
			statement.setInt(2, unit_usage);
			statement.setString(3, date);
			statement.setDouble(4, unit_price);
			statement.setDouble(5, total);
			statement.execute();
			statement.close();
			connection.close();
			setSuccessful("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setSuccessful("error");
		}
	}
	
	public String getBill() {
		
		Connection connection;
		PreparedStatement statement;
		String data="";
		
		try {
			
			connection = Connect.getConnection();
			statement = connection.prepareStatement("SELECT * FROM bill");
			
			ResultSet resultSet = statement.executeQuery();
			
			data = "<table><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>User ID</th>"
	                +"<th style='border: 1px solid black;'>Unit Usage</th>"
	                +"<th style='border: 1px solid black;'>Date</th>"
	                +"<th style='border: 1px solid black;'>Unit Price</th>"
	                +"<th style='border: 1px solid black;'>Total</th>"
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

	public void editBill(int id,int user_id,int unit_usage,String date,double unit_price,double total) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
				statement = connection.prepareStatement("UPDATE bill SET user_id=?, unit_usage=?, date=?, unit_price=?, total=? where id=?");
				statement.setInt(1, user_id);
				statement.setInt(2, unit_usage);
				statement.setString(3, date);
				statement.setDouble(4, unit_price);
				statement.setDouble(5, total);
				statement.setInt(8,id);
				statement.execute();
				statement.close();
				connection.close();
				setSuccessful("success");
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setSuccessful("error");
		}
	}

	public void deleteBill(int id) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
			statement = connection.prepareStatement("DELETE FROM bill WHERE id=?");
			statement.setInt(1, id);
			statement.execute();
			
			setSuccessful("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccessful("error");
		}
	}
	
}
