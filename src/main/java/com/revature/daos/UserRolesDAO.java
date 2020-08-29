package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.utilities.HibernateUtil;

public class UserRolesDAO implements IUserRolesDAO {
	
	@Override
	public UserRoles selectByUserRole(int userRoleId) {
		
		Session ses = HibernateUtil.getSession();
		
		List<UserRoles> urList = ses.createQuery("FROM UserRoles WHERE=" +userRoleId).list();
		
		UserRoles ur = urList.get(0);	//just lists the first one if there are more than one
		
		return ur;
	}
	
}
