package com.revature.daos;

import java.util.List;

import com.revature.models.LoginDTO;
import com.revature.models.Users;

public interface IUsersDAO {

	Users login(LoginDTO l);
	
	public List<Users> findAllUsers();
	
	public Users selectByUsersId(int usersId);
	
	public Users insert(Users u);
	
	public boolean updateUsers(Users u); 
	
	public Users selectByUserName(String userName);

	public Users selectByUserRoleId(int userRoleId);

	public boolean addUsers(Users u);
	
}

//need to have a method to view user role (maybe findByUserRoleId)
