package com.revature.daos;

public interface IReimbursementDAO {

	boolean updateReimbursementStatus(String reimbStatus, int reimbStatusId);

	boolean updateReimbursementType(String reimbType, int reimbTypeId);

}
