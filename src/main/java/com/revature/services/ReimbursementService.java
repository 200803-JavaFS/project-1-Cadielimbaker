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
	private static final Logger log = LogManager.getLogger(ReimbursementService.class); 
	
	
	public List<Reimbursement> findAllReimbursement() {
		log.info("find all reimbursements");
		return rdao.findAllReimbursement();
		
	}
	
	public Reimbursement findByUsersId(int reimbId) {
		log.info("Retrieving all reimbursements for user");
		return rdao.selectByReimbId(reimbId);
		
	}
	
	public boolean addReimbursement(AddReimbursementDTO ardto, int usersId) {
		log.info("Adding a reimbursement");
		Reimbursement r = new Reimbursement();
		r.setReimbAuthor(udao.selectByUsersId(usersId));
		r.setReimbAmount(ardto.reimbAmount);
		r.setReimbDescription(ardto.reimbDescription);
		r.setReimbTypeId(rdao.selectByReimbTypeId(ardto.reimbTypeId));
		r.setReimbStatusId(rdao.selectByReimbStatusId(ardto.reimbStatusId));
		if(rdao.addReimbursement(r)) {
			return true;
		}
		return false;
	}
	

//	public boolean updateReimbursement(ReimbursementDTO rdto, int usersId) {
//		log.info("Updating a reimbursement");
//		Reimbursement r = rdao.selectByReimbId(rdto.reimbId);
//		ReimbursementStatus rs = rdao.selectByReimbStatusId(rdto.reimbStatusId);
//		r.setReimbStatusId(rs);
//		//Users u = udao.selectByUsersId(reimbResolver);
//		r.setReimbResolver(udao.selectByUsersId(usersId));
//		//r.setReimbResolved(new Timestamp(System.currentTimeMillis()));
//		rdao.updateReimbursement(r);
//		System.out.println("Reimbursement was updated!");
//		return true;
//	}

	public boolean updateReimbursement(AddReimbursementDTO ardto) {
		log.info("Updating a reimbursement");
		Reimbursement r = rdao.selectByReimbId(ardto.reimbId);
		ReimbursementStatus rs = rdao.selectByReimbStatusId(ardto.reimbStatusId);
		r.setReimbStatusId(rs);
		//Users u = udao.selectByUsersId(reimbResolver);
		r.setReimbResolver(udao.selectByUserName(ardto.reimbAuthor));
		//r.setReimbResolved(new Timestamp(System.currentTimeMillis()));
		rdao.updateReimbursement(r);
		System.out.println("Reimbursement was updated!");
		return true;
	}

	public boolean addReimbursement(AddReimbursementDTO ardto) {
		Reimbursement r = new Reimbursement();
		double reimbAmount = ardto.reimbAmount;
		int reimbTypeId = ardto.reimbTypeId;
		
		r.setReimbAuthor(udao.selectByUserName(ardto.reimbAuthor));
		r.setReimbAmount(reimbAmount);
		r.setReimbDescription(ardto.reimbDescription);
		r.setReimbStatusId(rdao.selectByReimbStatusId(ardto.reimbStatusId));
		r.setReimbTypeId(rdao.selectByReimbTypeId(reimbTypeId));
		if(rdao.addReimbursement(r)) {
			return true;
		}
		return false;
}


	public Reimbursement selectByReimbId(int reimbId) {
		log.info("Getting reimbursements by reimbId");
		return rdao.selectByReimbId(reimbId);
	}

	public ReimbursementStatus selectByReimbStatusId(int reimbStatusId) {
		log.info("Retrieving reimbStatus by reimbStatusId");
		return rdao.selectByReimbStatusId(reimbStatusId);
	}

	public ReimbursementType selectByReimbTypeId(int reimbTypeId) {
		log.info("Retrieving reimbType by reimbTypeId");
		return rdao.selectByReimbTypeId(reimbTypeId);
	}

	public ReimbursementStatus addReimbursementStatus(ReimbursementStatus reimbStatus) {
		log.info("Adding a reimbursement status");
		return rdao.addReimbursementStatus(reimbStatus);
	}

	public ReimbursementType addReimbursementType(ReimbursementType reimbType) {
		log.info("Adding a reimbursement type");
		return rdao.addReimbursementType(reimbType);
	}

	public List<Reimbursement> findReimbursementByAuthor(int reimbAuthor) {
		log.info("Getting reimbursements by reimbAuthor");
		return rdao.findReimbursementByAuthor(reimbAuthor);
		
	}

	public List<Reimbursement> findAllReimbursementStatus(int reimbStatusId) {
		log.info("Getting reimbursements by reimbStatusId");
		return rdao.findAllReimbursementStatus(reimbStatusId);
	}
	
	public List<Reimbursement> findAllReimbursementType(int reimbTypeId) {
		log.info("Getting reimbursements by reimbTypeId");
		return rdao.findAllReimbursementType(reimbTypeId);
	}

	public Reimbursement selectByReimbResolved(Timestamp reimbResolved) {
		log.info("Getting reimbursements by reimbTypeId");
		return rdao.selectByReimbResolved(reimbResolved);
	}

	public List<Reimbursement> findReimbursementByReimbResolver(int reimbAuthor) {
		log.info("Getting reimbursements by reimbAuthor");
		return rdao.findReimbursementByAuthor(reimbAuthor);
	}
	
}
