package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Ers_Reimbursement_Type")
public class ReimbursementType implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Reimb_Type_ID")
	private int reimbTypeId;
	
	@Column(name="Reimb_Type")
	private ReimbursementType reimbType;
	
//All arguments	
	public ReimbursementType(int reimbTypeId, ReimbursementType reimbType) {
		super();
		this.reimbTypeId = reimbTypeId;
		this.reimbType = reimbType;
	}

//No reimbTypeId
	public ReimbursementType(ReimbursementType reimbType) {
		super();
		this.reimbType = reimbType;
	}

//No arguments
	public ReimbursementType() {
		super();
	}


	public int getReimbTypeId() {
		return reimbTypeId;
	}


	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}


	public ReimbursementType getReimbType() {
		return reimbType;
	}


	public void setReimbType(ReimbursementType reimbType) {
		this.reimbType = reimbType;
	}


	@Override
	public String toString() {
		return "ReimbursementType [reimbTypeId=" + reimbTypeId + ", reimbType=" + reimbType + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + reimbTypeId;
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
		ReimbursementType other = (ReimbursementType) obj;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (reimbTypeId != other.reimbTypeId)
			return false;
		return true;
	}
	
}
