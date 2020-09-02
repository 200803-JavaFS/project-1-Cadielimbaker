package com.revature;

import java.sql.Timestamp;
import java.time.LocalTime;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.IUserRolesDAO;
import com.revature.daos.IUsersDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserRolesDAO;
import com.revature.daos.UsersDAO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import java.sql.Timestamp;

public class Driver {

	public static IUsersDAO udao = new UsersDAO();
	public static IUserRolesDAO urdao = new UserRolesDAO();
	public static IReimbursementDAO rdao = new ReimbursementDAO();
	
	
	public static void main(String[]args) {
		//addUser1();
		//insertUser3();
		addReimbursement1();
		//addReimbursement2();
		
	}

//	private static void insertUser3() {
//		String p3 = "password4";
//		StringBuilder sb = new StringBuilder();
//		sb.append(p3.hashCode());
//		String hashp4 = sb.toString();
//		
//		UserRoles ur3 = urdao.selectByUserRole(1);
//		System.out.println(ur3);
//		Users u = new Users ("melissalimbaker",hashp4, "melissa", "limbaker", "melissalimbaker@email.com", ur3);
//
//		udao.insert(u);
//		System.out.println(u);
//		
//	}
	
	private static void addReimbursement1() {
		ReimbursementStatus rs1 = rdao.selectByReimbStatusId(1);
		ReimbursementType rt1 = rdao.selectByReimbTypeId(1);
		int usersId = 2;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Reimbursement r1 = new Reimbursement(150.00, timestamp , null, "new description",
				  udao.selectByUsersId(usersId), null, rs1, rt1);
		rdao.addReimbursement(r1);
		System.out.println(r1);
		
	}
	
//	private static void addReimbursement2() {
//		ReimbursementStatus rs1 = new ReimbursementStatus();
//				rdao.addReimbursementStatus(rs1);
//		ReimbursementType rt1 = new ReimbursementType();
//				rdao.addReimbursementType(rt1);
//		int usersId = 1;
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		Reimbursement r1 = new Reimbursement(150.00, timestamp , timestamp, "new description",
//				  udao.selectByUsersId(usersId), udao.selectByUsersId(usersId), rs1, rt1);
//		rdao.addReimbursement(r1);
//		System.out.println(r1);
		
	//}
//NOT USING
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
