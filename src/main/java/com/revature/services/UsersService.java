package com.revature.services;

import java.util.List;
import com.revature.daos.UsersDAO;
import com.revature.daos.IUsersDAO;
import com.revature.models.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsersService {
	
	private static IUsersDAO udao = new UsersDAO();
	private static final Logger log = LogManager.getLogger(UsersService.class);
	
	//might need to add List<Users> list = udao.findAllUsers(); then return list;
	public List<Users> findAllUsers() {
		return udao.findAllUsers();
		
		log.info("Retrieving all users";)
	}
	
	public Users findByUsersId(int usersId) {
		return udao.findByUsersId(usersId);
		log.info("Finding a user with id " + usersId);
	}
	
	public boolean addUsers(Users u) {
		return udao.addUsers(u);
	}

	//could be in place of addUsers
	//adds a user but checks to see if the user already exists based on user name, if so it will return a list of all accounts
		public boolean insertUsers(Users u) {

			if (u.getUserName() != null) {
				List<Users> list = udao.findAllUsers();
				boolean b = false;
				
				for (Users users : list) {
					if (users.getUserName().equals(u.getUserName())) {
						b = true;
					}
				}
				if (!b) {
					log.info("Inserting user: " + u);
					if (udao.addUsers(u)) {
						return true;
					}
				}
			}
			return false;
			
		}
	}
}
