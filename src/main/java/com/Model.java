package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class User 
{ private static final String telNo = null;


//A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.cj.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertService(String cus_nic, String month, String unit_calculation) { 			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into items ('id','name','nic','address' ,'telNo','accNo') values (?, ?, ?, ?, ?, ?)";
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						 preparedStmt.setInt(1, 0); 
						 preparedStmt.setString(2, name); 
						 preparedStmt.setString(3, nic); 
						 preparedStmt.setString(4, address); 
						 preparedStmt.setString(5, telNo); 
						 preparedStmt.setString(6, accNo)); 

						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newUser = readService(); 
						output = "{\"status\":\"success\",\"data\":\""+newUser+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the User.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readService() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<th> ID </th>"
					+ "<th> Name </th>"
					+ "<th> Nic </th>"
					+ "<th>Address</th>"
					+ "<th>TelNo</th>" 
					+ "<th>AccNo</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";
		
		 String query = "select p.address, p.name, i.id, i.nic, i.telNo, i.accNo from person p, User i where p.nic = i.nic"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 {  
		 
			String id = Integer.toString(rs.getInt("id"));
			String name = rs.getString("p.name");
			String nic = rs.getString("p.nic");				
			String accNo = rs.getString("i.accNo");
		 
		 
		 // Add into the html table
		 output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='"+id+"'>"+name+"</td>"; 
		 output += "<td>" + name + "</td>"; 
		 output += "<td>" + nic + "</td>"; 
		 output += "<td>" + accNo + "</td>"; 
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-Userid='" + id + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-Userid='" + id + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the Users."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateService(String id, String nic, String address, String accNo){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE User SET nic=?,name=?,accNo=? WHERE id=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, nic); 
							preparedStmt.setString(2, telNo); 
							preparedStmt.setString(3, accNo); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newUser = readService(); 
							output = "{\"status\":\"success\",\"data\":\""+newUser+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the User.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteService(String id){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from User where id=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(id)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newUsers = readService(); 
						 output = "{\"status\":\"success\",\"data\":\""+newUsers+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the User.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
