package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.services.UsersService;

public class UsersController {
	
	private static UsersService us = new UsersService();
	private static ObjectMapper om = new ObjectMapper();
	
	public void getUsers(HttpServletResponse res, int usersId) throws IOException {
		Users u = us.findByUsersId(usersId);
		if(u == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(u);
			res.getWriter().println(json);
		}
		
	}
	
	public void getUserRole(HttpServletResponse res, int userRoleId) throws IOException {
		UserRoles ur = us.findByUserRoleId(userRoleId);
		if(ur == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(ur);
			res.getWriter().println(json);
		}
		
	}
	public void getAllUsers(HttpServletResponse res) throws IOException {
		List<Users> all = us.findAllUsers();
		res.getWriter().println(om.writeValueAsString(all)); //in one line
		res.setStatus(200);
	}

	public void addUsers(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		System.out.println(body);
		
		Users u = om.readValue(body, Users.class);
		
		System.out.println(u);
		
		if (us.addUsers(u) != null) {
			res.setStatus(201);
			res.getWriter().println("A user was created");
		} else {
			res.setStatus(403);
		}
		
	}

}
