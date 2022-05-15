package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import db.Connect;

public class complaintModel {

	private String successful;
	
	public String getSuccessful() {
		return successful;
	}

	public void setSuccessful(String successful) {
		this.successful = successful;
	}

	public void addComplaint(int user_id,String type,String complaint,String date) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
			statement = connection.prepareStatement("insert into complaint (user_id,type,complaint,date) values (?,?,?,?)");
			statement.setInt(1, user_id);
			statement.setString(2, type);
			statement.setString(3, complaint);
			statement.setString(4, date);
			statement.execute();
			statement.close();
			connection.close();
			setSuccessful("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setSuccessful("error");
		}
	}
	
	public String getComplaint() {
		
		Connection connection;
		PreparedStatement statement;
		String data="";
		
		try {
			
			connection = Connect.getConnection();
			statement = connection.prepareStatement("SELECT * FROM complaint");
			
			ResultSet resultSet = statement.executeQuery();
			
			data = "<table><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>User ID</th>"
	                +"<th style='border: 1px solid black;'>Type</th>"
	                +"<th style='border: 1px solid black;'>Complaint</th>"
	                +"<th style='border: 1px solid black;'>Date</th>"
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

	public void editComplaint(int id,int user_id,String type,String complaint,String date) {
		Connection connection;
		PreparedStatement statement;
		ResultSet resultSet;
		String button = "<button type='button' onclick='edit("+resultSet.getString(1)+")' class='btn btn-primary'>Edit</button><br><button type='button' onclick='deletes("+resultSet.getString(1)+")' class='btn btn-warning'>Delete</button>";
		
		try {
			connection = Connect.getConnection();
			
				statement = connection.prepareStatement("UPDATE complaint SET user_id=?,type=?,complaint=?,date=? where id=?");
				statement.setInt(1, user_id);
				statement.setString(2, type);
				statement.setString(3, complaint);
				statement.setString(4, date);
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

	public void deleteComplaint(int id) {
		Connection connection;
		PreparedStatement statement;
		
		try {
			connection = Connect.getConnection();
			
			statement = connection.prepareStatement("DELETE FROM complaint WHERE id=?");
			statement.setInt(1, id);
			statement.execute();
			
			setSuccessful("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccessful("error");
		}
	}

	public static Object getOnecomplaint(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public JSONObject getOneComplaint(int id) {
		Connection connection;
		PreparedStatement preparedStatement;
		JSONObject json = new JSONObject();
		
		try {
			connection = Connect.getConnection();
			
			preparedStatement = connection.prepareStatement("SELECT * FROM complaint where id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				json.put("id", rs.getInt(1));
				json.put("user_id", rs.getString(2));
				json.put("type", rs.getString(3));
				json.put("complaint", rs.getString(4));
				json.put("date", rs.getString(5));
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccessful("unsuccess");
		}
		return json;
	}
