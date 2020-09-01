package com.revature.daos;

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

	public boolean addReimbursementType(ReimbursementType reimbType);

	public boolean addReimbursementWithType(Reimbursement r);

	public boolean addReimbursementStatus(ReimbursementStatus reimbStatus);

	public boolean addReimbursementWithStatus(Reimbursement r);

}
