package com.revature;

import java.sql.Timestamp;

import com.revature.daos.IUserRolesDAO;
import com.revature.daos.IUsersDAO;
import com.revature.daos.UserRolesDAO;
import com.revature.daos.UsersDAO;
import com.revature.models.Reimbursement;
import com.revature.models.UserRoles;
import com.revature.models.Users;

public class Driver {

	public static IUsersDAO udao = new UsersDAO();
	public static IUserRolesDAO urdao = new UserRolesDAO();
	
	public static void main(String[]args) {
		//addUser1();
		insertUser3();
		
	}
	
	private static void insertUser3() {
		String p3 = "password3";
		StringBuilder sb = new StringBuilder();
		sb.append(p3.hashCode());
		String hashp3 = sb.toString();
		
		UserRoles ur3 = urdao.selectByUserRole(1);
		System.out.println(ur3);
		Users u = new Users ("cadielimbaker",hashp3, "cadie", "limbaker", "cadielimbaker@email.com", ur3);
		//Users u = new Users ("melissalimbaker", hashp3, "melissa", "limbaker",
				//"melissalimbaker@email.com", ur3);
		udao.insert(u);
		System.out.println(u);
		
	}

//	public static void addUser1() {
//		String p1 = "password1";
//		StringBuilder sb = new StringBuilder();
//		sb.append(p1.hashCode());
//		String hashp1 = sb.toString();
//		
//		UserRoles ur1 = urdao.selectByUserRole(1);
//		Users u = new Users ("cadielimbaker", hashp1, "cadie", "limbaker",
//				"cadielimbaker@email.com", ur1);
//		//Users u = new Users (hashp1,"cadielimbaker@email.com", "cadie", "limbaker", "cadielimbaker",
//				// ur1);
//		udao.addUsers(u);
//		
//	}

}
