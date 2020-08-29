package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.utilities.ConnectionUtil;
import com.revature.utilities.HibernateUtil;
import com.revature.daos.UsersDAO;
import com.revature.daos.IUsersDAO;

public class ReimbursementDAO implements IReimbursementDAO{
	
	private IUsersDAO udao = new UsersDAO();
	
	@Override
	public void updateReimbursementStatus(String reimbStatus, int reimbStatusId) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.merge(reimbStatus);
	}
	

	@Override
	public void updateReimbursementType(String reimbType, int reimbTypeId) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.merge(reimbType);
	}
	
	@Override
	public Reimbursement selectByReimbId(int reimbId) {
		
		Session ses = HibernateUtil.getSession();
		
		Reimbursement r = ses.get(Reimbursement.class, reimbId);
		
		return r;
	}
	
	@Override
	public List<Reimbursement> findAllReimbursement() {
		
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("FROM Reimbursement").list();
		return rList;
	}
	
	@Override
	public boolean addReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.save(r);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
