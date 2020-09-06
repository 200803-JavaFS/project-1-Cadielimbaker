package com.revature.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.IUserRolesDAO;
import com.revature.daos.IUsersDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserRolesDAO;
import com.revature.daos.UsersDAO;
import com.revature.models.AddReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.UserRoles;
import com.revature.models.Users;

public class ReimbursementService {

	private static IReimbursementDAO rdao = new ReimbursementDAO();
	private static IUserRolesDAO urdao = new UserRolesDAO();
	private static IUsersDAO udao = new UsersDAO();
	private static final Logger log = LogManager.getLogger(UsersService.class); //WOULD LIKE TO FIGURE OUT HOW TO IMPLEMENT LOGGING
	
	
	public List<Reimbursement> findAllReimbursement() {
		return rdao.findAllReimbursement();
		
	}
	
	public Reimbursement findByUsersId(int reimbId) {
		return rdao.selectByReimbId(reimbId);
		
	}
	
	public boolean addReimbursement(AddReimbursementDTO ardto, int usersId) {
		Reimbursement r = new Reimbursement();
		r.setReimbAuthor(udao.selectByUsersId(usersId));
		r.setReimbAmount(ardto.reimbAmount);
		r.setReimbDescription(ardto.reimbDescription);
		r.setReimbTypeId(rdao.selectByReimbTypeId(ardto.reimbTypeId));
		if(rdao.addReimbursement(r)) {
			return true;
		}
		return false;
	}
	

	public boolean updateReimbursement(ReimbursementDTO rdto, int usersId) {
		Reimbursement r = rdao.selectByReimbId(rdto.reimbId);
		ReimbursementStatus rs = rdao.selectByReimbStatusId(rdto.reimbStatusId);
		r.setReimbStatusId(rs);
		//Users u = udao.selectByUsersId(reimbResolver);
		r.setReimbResolver(udao.selectByUsersId(usersId));
		//r.setReimbResolved(new Timestamp(System.currentTimeMillis()));
		rdao.updateReimbursement(r);
		System.out.println("Reimbursement was updated!");
		return true;
	}


	public Reimbursement selectByReimbId(int reimbId) {
		return rdao.selectByReimbId(reimbId);
	}

	public ReimbursementStatus selectByReimbStatusId(int reimbStatusId) {
		return rdao.selectByReimbStatusId(reimbStatusId);
	}

	public ReimbursementType selectByReimbTypeId(int reimbTypeId) {
		return rdao.selectByReimbTypeId(reimbTypeId);
	}

	public ReimbursementStatus addReimbursementStatus(ReimbursementStatus reimbStatus) {
		return rdao.addReimbursementStatus(reimbStatus);
	}

	public ReimbursementType addReimbursementType(ReimbursementType reimbType) {
		return rdao.addReimbursementType(reimbType);
	}

	public List<Reimbursement> findReimbursementByAuthor(int reimbAuthor) {
		return rdao.findReimbursementByAuthor(reimbAuthor);
		
	}

	public List<Reimbursement> findAllReimbursementStatus(int reimbStatusId) {
		return rdao.findAllReimbursementStatus(reimbStatusId);
	}
	
	public List<Reimbursement> findAllReimbursementType(int reimbTypeId) {
		return rdao.findAllReimbursementType(reimbTypeId);
	}

	public Reimbursement selectByReimbResolved(Timestamp reimbResolved) {
		return rdao.selectByReimbResolved(reimbResolved);
	}

	public List<Reimbursement> findReimbursementByReimbResolver(int reimbAuthor) {
		return rdao.findReimbursementByAuthor(reimbAuthor);
	}
}
