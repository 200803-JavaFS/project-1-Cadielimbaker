package com.revature.daos;

import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.daos.UserRolesDAO;

public interface IUserRolesDAO {

	public UserRoles selectByUserRole(int userRoleId);
	
	public boolean addUserRoles(UserRoles userRole);

	public boolean addUsersWithUserRoles(Users u);
}
