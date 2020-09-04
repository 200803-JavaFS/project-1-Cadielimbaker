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
	private static final Logger log = LogManager.getLogger(UsersService.class); //WOULD LIKE TO FIGURE OUT HOW TO IMPLEMENT LOGGING
	
	//might need to add List<Users> list = udao.findAllUsers(); then return list;
	public List<Users> findAllUsers() {
		return udao.findAllUsers();
		
	}
	
	public Users findByUsersId(int usersId) {
		return udao.selectByUsersId(usersId);
		
	}
	
	public Users addUsers(Users u) {
		return udao.insert(u);
	}

	public UserRoles findByUserRoleId(int userRoleId) {
		return urdao.selectByUserRole(userRoleId);
	}

	public UserRoles addUserRoles(UserRoles userRole) {
		urdao.addUserRoles(userRole);
		return null;
	}
		
	public Users selectByUsername(String userName) {
		return udao.selectByUserName(userName);
	}

}
