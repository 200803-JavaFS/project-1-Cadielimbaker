package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Users;
import com.revature.models.LoginDTO;
import com.revature.models.Reimbursement;
import com.revature.utilities.ConnectionUtil;

public class UsersDAO implements IUsersDAO{

	@Override
	public List<Users> findAllUsers() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM Ers_Users;";
			
			Statement statement = conn.createStatement();
			
			List<Users> list = new ArrayList<>(); 
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				Users u = new Users();
				
				u.setUsersId(result.getInt("Ers_Users_ID"));
				u.setUserName(result.getString("Ers_Username"));
				u.setPassword(result.getString("Ers_Password"));
				u.setUserFirstName(result.getString("User_First_Name"));
				u.setUserLastName(result.getString("User_Last_Name"));
				u.setUserEmail(result.getString("User_Email"));
				u.setUserRoleId(result.getInt("User_Role_ID_FK"));
				
				list.add(u); 
			}
			
			return list;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Users findByUsersId(int usersId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM Ers_Users WHERE Ers_Users_ID = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, usersId);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				Users u = new Users();
				
				u.setUsersId(result.getInt("Ers_Users_ID"));
				u.setUserName(result.getString("Ers_Username"));
				u.setPassword(result.getString("Ers_Password"));
				u.setUserFirstName(result.getString("User_First_Name"));
				u.setUserLastName(result.getString("User_Last_Name"));
				u.setUserEmail(result.getString("User_Email"));
				u.setUserRoleId(result.getInt("User_Role_ID_FK"));
				
				return(u); 
				
			} else {
				//good place to log a failed query.
				System.out.println("No user Id found: incorrect user Id entered or need to create a new user profile");
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addUsers(Users u) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO Ers_Users (Ers_username, Ers_Password, User_First_Name, User_Last_Name, User_Email, User_Role_ID_FK )"
					+ "VALUES (?, ?, ?, ?, ?, ?);"; 
		
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			
			statement.setString(++index, u.getUserName());
			statement.setString(++index, u.getPassword());
			statement.setString(++index, u.getUserFirstName());
			statement.setString(++index, u.getUserLastName());
			statement.setString(++index, u.getUserEmail());
			statement.setInt(++index, u.getUserRoleId());
			
			statement.execute();
			return true; 
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean updateUsers(Users u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Ers_Users SET Ers_username = ?, Ers_Password = ?, User_First_Name = ?, User_Last_Name = ?, User_Email = ?  WHERE Ers_Users_Id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			
			statement.setString(++index, u.getUserName());
			statement.setString(++index, u.getPassword());
			statement.setString(++index, u.getUserFirstName());
			statement.setString(++index, u.getUserLastName());
			statement.setString(++index, u.getUserEmail());
			
			statement.execute();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	//Bank's normally do not delete information, but I have added this method just in case
	@Override
	public boolean deleteUsers(int UsersId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM Users WHERE Id =" + Id + ";";

			Statement statement = conn.createStatement();

			statement.execute(sql);
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Users login(LoginDTO l) {
		
		Users u = new Users();
		String sql = "SELECT * FROM Ers_Users WHERE Ers_Username = ?, Ers_Password = ?;";
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(0, l.userName);
			statement.setString(1, l.password);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				u.setUsersId(result.getInt("Ers_Users_ID"));
				u.setUserName(result.getString("Ers_Username"));
				u.setPassword(result.getString("Ers_Password"));
				u.setUserFirstName(result.getString("User_First_Name"));
				u.setUserLastName(result.getString("User_Last_Name"));
				u.setUserEmail(result.getString("User_Email"));
				u.setUserRoleId(result.getInt("User_Role_ID_FK"));
				
				//return(u); 
				
			} else {
				//good place to log a failed query.
				System.out.println("No login combination found: incorrect userName/password entered or need to create a new user profile");
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
}
}
