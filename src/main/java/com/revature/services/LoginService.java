package com.revature.services;

import com.revature.models.LoginDTO;
import com.revature.models.Users;

public class LoginService {
	
	public boolean login(LoginDTO l) {
		if(l.userName.equals(Users.getUserName()) && l.password.equals(Users.getPassword())) {
			return true;
		}
		return false; 
	}

}
