package com.revature.daos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;

public interface IReimbursementDAO {

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

	public List<Reimbursement> findAllReimbursementType(int reimbTypeId);

	public Reimbursement selectByReimbResolved(Timestamp reimbResolved);

	public boolean updateReimbursement(Reimbursement r);
	
	public List<Reimbursement> findReimbursementByReimbResolver(int reimbAuthor);

	public List<Reimbursement> findAllReimbursementStatus(int reimbStatusId);

}
