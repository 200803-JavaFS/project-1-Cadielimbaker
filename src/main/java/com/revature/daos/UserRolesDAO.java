package com.revature.daos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.utilities.HibernateUtil;

public class UserRolesDAO implements IUserRolesDAO {
	
	private static final Logger log = LogManager.getLogger(UserRolesDAO.class);
	
	@Override
	public UserRoles selectByUserRole(int userRoleId) {
		
		Session ses = HibernateUtil.getSession();

		UserRoles ur = ses.get(UserRoles.class, userRoleId);
		return ur;
		
//		Session ses = HibernateUtil.getSession();
//		
//		List<UserRoles> urList = ses.createQuery("FROM UserRoles WHERE userRoleId=" +userRoleId).list();
//		
//		UserRoles ur = urList.get(1);	//just lists the first one if there are more than one
//		
//		return ur;
	}
	
	@Override
	public boolean addUserRoles(UserRoles userRole) {
		log.info("@addUserRoles in UserRolesDAO");
		Session ses = HibernateUtil.getSession();
	
		try {
			ses.save(userRole);
			return true;
		}catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addUsersWithUserRoles(Users u) {
		Session ses = HibernateUtil.getSession();

		Transaction tx = ses.beginTransaction();

		try {
			IUserRolesDAO urdao = new UserRolesDAO();
			urdao.addUserRoles(u.getUserRoleId());
			ses.saveOrUpdate(u);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
	}
}
