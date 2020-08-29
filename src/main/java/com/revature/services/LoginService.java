package com.revature.services;

import com.revature.daos.UsersDAO;
import com.revature.models.LoginDTO;
import com.revature.models.Users;

public class LoginService {
	UsersDAO udao = new UsersDAO();
	public boolean login(LoginDTO l) {
		Users u = udao.selectByUserName(l.userName);
		if((u!=null) && l.password.equals(u.getPassword())) {
			return true;
		
		}else {
		return false; 
	}

}
}
