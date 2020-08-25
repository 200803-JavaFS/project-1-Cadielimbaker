package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.utilities.ConnectionUtil;
import com.revature.daos.UsersDAO;
import com.revature.daos.IUsersDAO;

public class ReimbursementDAO implements IReimbursementDAO{
	
	private IUsersDAO udao = new UsersDAO();
	
	@Override
	//Finding all the accounts
	public List<Account> findAll() {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "SELECT * FROM Account;";
			
			Statement statement = conn.createStatement();
			List<Account> list = new ArrayList<>();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				Account acct = new Account();
				acct.setAccountId(result.getInt("accountId"));	//This is pulling an AccountId string from the AccountId column 
																	//and set it as the AccountId field in my new Account object
				acct.setAccountType(result.getString("accountType"));
				acct.setId(result.getInt("Id_fk"));
				acct.setAccountStatus(result.getString("accountStatus"));
				acct.setBalance(result.getDouble("balance"));
				list.add(acct);
				
				}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public Account findByAccountId(int accountId) {
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "SELECT * FROM Account WHERE AccountId = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountId);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				Account acct = new Account();
				acct.setAccountId(result.getInt("accountId"));	
				acct.setAccountType(result.getString("accountType"));
				acct.setId(result.getInt("id_fk"));
				acct.setAccountStatus(result.getString("accountStatus"));
				acct.setBalance(result.getDouble("balance"));
				
				return acct;
				
			} else {
				//log a failed query
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addAccount(Account acct) {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "INSERT INTO Account (accountType, Id_fk, accountStatus, balance)"
					+ "VALUES (?,?, ?, ?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, acct.getAccountType());
			statement.setInt(++index, acct.getId());
			statement.setString(++index, acct.getAccountStatus());
			statement.setDouble(++index, acct.getBalance());
			
			statement.execute();
			return true; 
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	//DO THIS ONE LIKE THE UPDATE USER IN THE USERDAO
	@Override
	public boolean updateAccount(Account acct) {
		try (Connection conn = ConnectionUtility.getConnection()) {
			String sql = "UPDATE Account SET accountType = ?, accountStatus = ?, balance = ?"
					+ " WHERE accountId = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, acct.getAccountType());
			statement.setString(++index, acct.getAccountStatus());
			statement.setDouble(++index, acct.getBalance());
			
			statement.execute();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean updateReimbursementStatus(String reimbStatus, int reimbStatusId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Ers_Reimbursement_Status SET Reimb_Status = ?"
					+ " WHERE Reimb_Status_ID = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;

			statement.setString(++index, reimbStatus);
			statement.setInt(++index, reimbStatusId);
			
			statement.execute();
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	


	@Override
	public boolean updateReimbursementType(String reimbType, int reimbTypeId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Ers_Reimbursement_Type SET Reimb_Type = ?"
					+ " WHERE Reimb_Type_ID = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;

			statement.setString(++index, reimbType);
			statement.setInt(++index, reimbTypeId);
			
			statement.execute();
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
