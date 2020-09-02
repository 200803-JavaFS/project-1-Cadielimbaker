package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Ers_Reimbursement_Status")
public class ReimbursementStatus implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Reimb_Status_ID")
	private int reimbStatusId;
	
	@Column(name="Reimb_Status", nullable=false)
	private String reimbStatus;
	
//All arguments constructor	
	public ReimbursementStatus(int reimbStatusId, String reimbStatus) {
		super();
		this.reimbStatusId = reimbStatusId;
		this.reimbStatus = reimbStatus;
	}

//No reimbStatusId in constructor
	public ReimbursementStatus(String reimbStatus) {
		super();
		this.reimbStatus = reimbStatus;
	}

//No arguments constructor
	public ReimbursementStatus() {
		super();
	}


	public int getReimbStatusId() {
		return reimbStatusId;
	}


	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}


	public String getReimbStatus() {
		return reimbStatus;
	}


	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [reimbStatusId=" + reimbStatusId + ", reimbStatus=" + reimbStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + reimbStatusId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		return true;
	}


	
	
}
