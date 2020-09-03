package com.revature.services;

import com.revature.daos.UsersDAO;
import com.revature.models.LoginDTO;
import com.revature.models.Users;

public class LoginService {
	UsersDAO udao = new UsersDAO();
	public boolean login(LoginDTO l) {
		
			Users u = udao.selectByUserName(l.userName);
			StringBuilder tempPassword = new StringBuilder();
			tempPassword.append(l.password.hashCode());
			String hashPassword = tempPassword.toString();
			
			if((u!=null) && hashPassword.equals(u.getPassword())) {
				return true;
			
			}else {
			return false; 
		}

	}
		
		
		
		
	//UsersDAO udao = new UsersDAO();	
//	Users u = udao.selectByUserName(l.userName);
//		
//		StringBuilder tempPassword = new StringBuilder();
//		tempPassword.append(l.password.hashCode());
//		String hashPassword = tempPassword.toString();
//		
//		if((u!=null) && hashPassword.equals(u.getPassword())) {
//			getUsers(l.userName);
//			 return true;
//			
//		}else {
//		return false; 
//	}
//
//}
//	public Users getUsers(String userName) {
//		return udao.selectByUserName(userName);
//		
//	}
}
