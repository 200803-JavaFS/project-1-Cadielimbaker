package com.revature.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.utilities.ConnectionUtil;
import com.revature.utilities.HibernateUtil;
import com.revature.daos.UsersDAO;
import com.revature.daos.IUsersDAO;

public class ReimbursementDAO implements IReimbursementDAO{
	
	private IUsersDAO udao = new UsersDAO();
	//private IReimbursementDAO rdao = new ReimbursementDAO();
	
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


	@Override
	public ReimbursementStatus selectByReimbStatusId(int reimbStatusId) {
		//Session ses = HibernateUtil.getSession();
		
		//ReimbursementStatus rstatus = ses.get(ReimbursementStatus.class, reimbStatusId);
		
		//return rstatus;
		
		Session ses = HibernateUtil.getSession();
		
		try {
			ReimbursementStatus rstatus = ses.get(ReimbursementStatus.class, reimbStatusId);
			return rstatus;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	
	}


	@Override
	public ReimbursementType selectByReimbTypeId(int reimbTypeId) {
		Session ses = HibernateUtil.getSession();
		
		ReimbursementType rtype = ses.get(ReimbursementType.class, reimbTypeId);
		
		return rtype;
	}
	
	@Override
	public ReimbursementType addReimbursementType(ReimbursementType reimbType) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.save(reimbType);
			//return true;
		}catch (HibernateException e) {
			e.printStackTrace();
			//return false;
		}
		return reimbType;
		
	}
	
	@Override
	public boolean addReimbursementWithType(Reimbursement r) {
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		try {
			IReimbursementDAO rdao = new ReimbursementDAO();
			rdao.addReimbursementType(r.getReimbTypeId());
			ses.saveOrUpdate(r);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
	}
	
	@Override
	public ReimbursementStatus addReimbursementStatus(ReimbursementStatus reimbStatus) {
		Session ses = HibernateUtil.getSession();
		
		try {
			 ses.save(reimbStatus);
			//return true;
		}catch (HibernateException e) {
			e.printStackTrace();
			//return false;
		}
		return reimbStatus;
		
	}
	
	@Override
	public boolean addReimbursementWithStatus(Reimbursement r) {
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		try {
			IReimbursementDAO rdao = new ReimbursementDAO();
			rdao.addReimbursementStatus(r.getReimbStatusId());
			ses.saveOrUpdate(r);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
	}


	@Override
	public List<Reimbursement> findReimbursementByAuthor(int reimbAuthor) {
			
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> tickets = ses.createQuery("FROM Reimbursement WHERE reimbAuthor=" +reimbAuthor).list();
		return tickets;
	}


}
