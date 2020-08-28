package com.revature.services;

import java.util.List;
import com.revature.daos.UsersDAO;
import com.revature.daos.IUsersDAO;
import com.revature.models.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsersService {
	
	private static IUsersDAO udao = new UsersDAO();
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
		
}
