package com.revature.services;

import java.util.List;
import com.revature.daos.UsersDAO;
import com.revature.daos.IUserRolesDAO;
import com.revature.daos.IUsersDAO;
import com.revature.daos.UserRolesDAO;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsersService {
	
	private static IUsersDAO udao = new UsersDAO();
	private static IUserRolesDAO urdao = new UserRolesDAO();
	private static final Logger log = LogManager.getLogger(UsersService.class); 
	
	
	public List<Users> findAllUsers() {
		log.info("Finding all users");
		return udao.findAllUsers();
		
	}
	
	public Users findByUsersId(int usersId) {
		log.info("Getting a user by usersId");
		return udao.selectByUsersId(usersId);
		
	}
	
	public Users addUsers(Users u) {
		log.info("Add user");
		return udao.insert(u);
	}

	public UserRoles findByUserRoleId(int userRoleId) {
		log.info("Getting user roles by userRoleId");
		return urdao.selectByUserRole(userRoleId);
	}

	public UserRoles addUserRoles(UserRoles userRole) {
		log.info("Adding a user role");
		urdao.addUserRoles(userRole);
		return null;
	}
		
	public Users selectByUsername(String userName) {
		log.info("Finding user by userName");
		return udao.selectByUserName(userName);
	}

}
