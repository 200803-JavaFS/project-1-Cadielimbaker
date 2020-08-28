package com.revature.daos;

import com.revature.models.UserRoles;
import com.revature.daos.UserRolesDAO;

public interface IUserRolesDAO {

	public UserRoles selectByUserRole(int userRoleId);
}
