package com.revature.daos;

import java.util.List;

import com.revature.models.LoginDTO;
import com.revature.models.Users;

public interface IUsersDAO {

	Users login(LoginDTO l);
	
	public List<Users> findAllUsers();
	
	public Users findByUsersId(int usersId);
	
	public boolean addUsers(Users u);
	
	public boolean updateUsers(Users u); 
	
	//typically Banks do not delete information, but I included it here just in case
	public boolean deleteUsers(int usersId);
}

//need to have a method to view user role (maybe findByUserRoleId)
