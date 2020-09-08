package com.revature.services;

import com.revature.daos.UsersDAO;
import com.revature.models.LoginDTO;
import com.revature.models.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginService {
	
	private static final Logger log = LogManager.getLogger(UsersDAO.class);
	private static final Logger Log = LogManager.getLogger(LoginService.class);
	
	UsersDAO udao = new UsersDAO();
	
	public boolean login(LoginDTO l) { 
    	log.info("@login in LoginService");
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
}
	
//	public boolean login(LoginDTO l) {
//		log.info("@login in LoginService");
//			Users u = udao.selectByUserName(l.userName);
//			System.out.println(u.getPassword());
//			StringBuilder tempPassword = new StringBuilder();
//			tempPassword.append(l.password.hashCode());
//			String hashPassword = tempPassword.toString();
//			System.out.println(hashPassword);
//			if((u!=null) && hashPassword.equals(u.getPassword())) {
//				return true;
//			
//			}else {
//			return false; 
//		}
//
//	}
//	}	
		
		
		


