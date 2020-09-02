package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;

public interface IReimbursementDAO {

	public void updateReimbursementStatus(String reimbStatus, int reimbStatusId);

	public void updateReimbursementType(String reimbType, int reimbTypeId);

	public Reimbursement selectByReimbId(int reimbId);
	
	public ReimbursementStatus selectByReimbStatusId(int reimbStatusId);
	
	public ReimbursementType selectByReimbTypeId(int reimbTypeId);

	public boolean addReimbursement(Reimbursement r);

	public List<Reimbursement> findAllReimbursement();

	public ReimbursementType addReimbursementType(ReimbursementType reimbType);

	public boolean addReimbursementWithType(Reimbursement r);

	public ReimbursementStatus addReimbursementStatus(ReimbursementStatus reimbStatus);

	public boolean addReimbursementWithStatus(Reimbursement r);

	public List<Reimbursement> findReimbursementByAuthor(int reimbAuthor);
	
	public List<ReimbursementStatus> findAllReimbursementStatus();

	public List<ReimbursementType> findAllReimbursementType();

}
