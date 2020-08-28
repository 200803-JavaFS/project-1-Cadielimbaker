package com.revature.daos;

import java.util.List;

import com.revature.models.LoginDTO;
import com.revature.models.Users;

public interface IUsersDAO {

	Users login(LoginDTO l);
	
	public List<Users> findAllUsers();
	
	public Users selectByUsersId(int usersId);
	
	public Users insert(Users u);
	
	public void updateUsers(Users u); 
	
	public Users selectByUserName(String userName);
	
}

//need to have a method to view user role (maybe findByUserRoleId)
