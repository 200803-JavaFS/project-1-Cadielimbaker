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
		insertUser2();
		
	}
	
	private static void insertUser2() {
		String p2 = "password2";
		StringBuilder sb = new StringBuilder();
		sb.append(p2.hashCode());
		String hashp2 = sb.toString();
		
		UserRoles ur2 = urdao.selectByUserRole(1);
		System.out.println(ur2);
		Users u = new Users ("carolinelimbaker", hashp2, "caroline", "limbaker",
				"carolinelimbaker@email.com", ur2);
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
