package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface IReimbursementDAO {

	public void updateReimbursementStatus(String reimbStatus, int reimbStatusId);

	public void updateReimbursementType(String reimbType, int reimbTypeId);

	public Reimbursement selectByReimbId(int reimbId);

	public boolean addReimbursement(Reimbursement r);

	public List<Reimbursement> findAllReimbursement();

}
