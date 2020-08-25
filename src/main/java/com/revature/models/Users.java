package com.revature.models;

import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Arrays;

public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int usersId;
	private String userName;
	private String password;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private int userRoleId;
	
	
//This is an all arguments constructor
	public Users(int usersId, String userName, String password, String userFirstName, String userLastName,
			String userEmail, int userRoleId) {
		super();
		this.usersId = usersId;
		this.userName = userName;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userRoleId = userRoleId;
	}

	
	
//This constructor has no userId because of serialization
	public Users(String userName, String password, String userFirstName, String userLastName, String userEmail,
			int userRoleId) {
		super();
		this.userName = userName;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userRoleId = userRoleId;
	}



//This constructor has no userId and no userRoleId because of serialization
	public Users(String userName, String password, String userFirstName, String userLastName, String userEmail) {
		super();
		this.userName = userName;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
	}


//This is a no arguments constructor
	public Users() {
		super();
	}



	public int getUsersId() {
		return usersId;
	}
	
	
	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	
	
	public String getUserName() {
		return userName;
	}
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getUserFirstName() {
		return userFirstName;
	}
	
	
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	
	public String getUserLastName() {
		return userLastName;
	}
	
	
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	
	public String getUserEmail() {
		return userEmail;
	}
	
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	public int getUserRoleId() {
		return userRoleId;
	}
	
	
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}



	@Override
	public String toString() {
		return "Users [usersId=" + usersId + ", userName=" + userName + ", password=" + password + ", userFirstName="
				+ userFirstName + ", userLastName=" + userLastName + ", userEmail=" + userEmail + ", userRoleId="
				+ userRoleId + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + userRoleId;
		result = prime * result + usersId;
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
		Users other = (Users) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userFirstName == null) {
			if (other.userFirstName != null)
				return false;
		} else if (!userFirstName.equals(other.userFirstName))
			return false;
		if (userLastName == null) {
			if (other.userLastName != null)
				return false;
		} else if (!userLastName.equals(other.userLastName))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userRoleId != other.userRoleId)
			return false;
		if (usersId != other.usersId)
			return false;
		return true;
	}
	
}
