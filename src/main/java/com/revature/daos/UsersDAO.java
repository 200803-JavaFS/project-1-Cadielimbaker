package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Users;
import com.revature.models.LoginDTO;
import com.revature.models.Reimbursement;
import com.revature.models.UserRoles;
import com.revature.utilities.ConnectionUtil;
import com.revature.utilities.HibernateUtil;

public class UsersDAO implements IUsersDAO{

	@Override
	public Users insert(Users u) {

		Session ses = HibernateUtil.getSession();
		
		Transaction tr = ses.beginTransaction();
		
		ses.save(u);
	
		tr.commit();	//inserts into the database table
		//only doing one function so you don't really need this as a transaction
		
		return u;
	}
	
	@Override
	public boolean addUsers(Users u) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.save(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUsers(Users u) {
	
		Session ses = HibernateUtil.getSession();
		
		try {
		ses.merge(u);
		return true;
		
	}catch(HibernateException e) {
		e.printStackTrace();
		return false;
	}
	}
	
	@Override
	public Users selectByUsersId(int usersId) {
		
		Session ses = HibernateUtil.getSession();
		
		Users u = ses.get(Users.class, usersId);
		
		return u;
	}
	
	@Override
	public Users selectByUserRoleId(int userRoleId) {
		
		Session ses = HibernateUtil.getSession();
		
		Users u = ses.get(Users.class, userRoleId);
		
		return u;
	}
	
	@Override
	public Users selectByUserName(String userName) {
		
		Session ses = HibernateUtil.getSession();
		
		List<Users> uList = ses.createQuery("FROM Users WHERE userName='" +userName+"'").list();
		
		Users u = uList.get(0);	//just lists the first one if there are more than one
		
		return u;
	}
	
	@Override
	public List<Users> findAllUsers() {
		
		Session ses = HibernateUtil.getSession();
		List<Users> uList = ses.createQuery("FROM Users").list();
		return uList;
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
				//u.setUserRoleId(result.getInt("User_Role_ID_FK"));
				
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

