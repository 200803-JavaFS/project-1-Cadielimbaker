package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

public class ReimbursementService {

	private static IReimbursementDAO rdao = new ReimbursementDAO();
	private static IUserRolesDAO urdao = new UserRolesDAO();
	private static final Logger log = LogManager.getLogger(UsersService.class); //WOULD LIKE TO FIGURE OUT HOW TO IMPLEMENT LOGGING
	
	
	public List<Reimbursement> findAllReimbursement() {
		return rdao.findAllReimbursement();
		
	}
	
	public Reimbursement findByUsersId(int reimbId) {
		return rdao.selectByReimbId(reimbId);
		
	}
	
	public boolean addReimbursement(Reimbursement r) {
		return rdao.addReimbursement(r);
	}

	public void updateReimbursementStatus(String reimbStatus, int reimbStatusId) {
		rdao.updateReimbursementStatus(reimbStatus, reimbStatusId);
	}

	public void updateReimbursementType(String reimbType, int reimbTypeId) {
		rdao.updateReimbursementType(reimbType, reimbTypeId);
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

}
