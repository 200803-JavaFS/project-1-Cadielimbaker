package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.utilities.ConnectionUtil;
import com.revature.utilities.HibernateUtil;
import com.revature.daos.UsersDAO;
import com.revature.daos.IUsersDAO;

public class ReimbursementDAO implements IReimbursementDAO{
	
	private IUsersDAO udao = new UsersDAO();
	
	public void updateUsers(Users u) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.merge(u);
	}
	
	public void updateReimbursementStatus(String reimbStatus, int reimbStatusId) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.merge(reimbStatus);
	}
	

	@Override
	public void updateReimbursementType(String reimbType, int reimbTypeId) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.merge(reimbType);
	}

}
