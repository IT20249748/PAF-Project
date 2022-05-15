package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.Connect;

public class paymentModel {

	private String successful;
	
	public String getSuccessful() {
		return successful;
	}

	public void setSuccessful(String successful) {
		this.successful = successful;
	}

	public void addPayment(int bill_id,String card_number,String card_type,double amount) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
			statement = connection.prepareStatement("insert into payment (bill_id, card_number, card_type, amount) values (?,?,?,?)");
			statement.setInt(1, bill_id);
			statement.setString(2, card_number);
			statement.setString(3, card_type);
			statement.setDouble(4, amount);
			statement.execute();
			statement.close();
			connection.close();
			setSuccessful("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setSuccessful("error");
		}
	}
	
	public String getPayment() {
		
		Connection connection;
		PreparedStatement statement;
		String data="";
		
		try {
			
			connection = Connect.getConnection();
			statement = connection.prepareStatement("SELECT * FROM payment");
			
			ResultSet resultSet = statement.executeQuery();
			
			data = "<table><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Bill Id</th>"
	                +"<th style='border: 1px solid black;'>Card Number</th>"
	                +"<th style='border: 1px solid black;'>Card Type</th>"
	                +"<th style='border: 1px solid black;'>Amount</th>"
	                +"<th style='border: 1px solid black;'>Action</th>"
	                +"</tr>"
	            +"</thead><tbody>";
			
			while (resultSet.next()) {
				
				data = data+"<tr><td style='border: 1px solid black;'>"+resultSet.getString(1)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(4)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(5)+"</td>"
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

	public void editPayment(int id,int bill_id,String card_number,String card_type,double amount) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
				statement = connection.prepareStatement("UPDATE payment SET bill_id=?, card_number=?, card_type=?, amount=? where id=?");
				statement.setInt(1, bill_id);
				statement.setString(2, card_number);
				statement.setString(3, card_type);
				statement.setDouble(4, amount);
				statement.setInt(5,id);
				statement.execute();
				statement.close();
				connection.close();
				setSuccessful("success");
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setSuccessful("error");
		}
	}

	public void deletePayment(int id) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
			statement = connection.prepareStatement("DELETE FROM payment WHERE id=?");
			statement.setInt(1, id);
			statement.execute();
			
			setSuccessful("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccessful("error");
		}
	}
	
}
