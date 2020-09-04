package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UsersController;
import com.revature.controllers.UsersController;
import com.revature.models.LoginDTO;
import com.revature.models.Users;
import com.revature.services.UsersService;

public class MasterServlet extends HttpServlet {

	private static UsersController uc = new UsersController();
	private static LoginController lc = new LoginController();
	private static ReimbursementController rc = new ReimbursementController();
	private static UsersService us = new UsersService();
	public MasterServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		// By default tomcat will send back a successful status code if it finds a
		// servlet method.
		// Because all requests will hit this method, we are defaulting to not found and
		// will override for success requests.
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/project1/", "");

		// example URI = avenger/1 to get avenger with ID 1

		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));
		if(portions.length==0) {
			req.getRequestDispatcher("index.html").forward(req, res);
		}

		try {
			switch (portions[0]) {
			case "Users":
				if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("Loggedin")) {
					if (req.getMethod().equals("GET")) {
						if (portions.length == 2) {
							int userId = Integer.parseInt(portions[1]);
							uc.getUsers(res, userId);
						} else if (portions.length == 1) {
							uc.getAllUsers(res);
						}
					} else if (req.getMethod().equals("POST")) {
						uc.addUsers(req, res);
					}
				} else {
					res.setStatus(403);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
				
			case "UserRoles":
				if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("Loggedin")) {
					if (req.getMethod().equals("GET")) {
						if (portions.length == 2) {
							int userRolesId = Integer.parseInt(portions[1]);
							uc.getUserRole(res, userRolesId);
						} 
					} else if (req.getMethod().equals("POST")) {
						uc.addUserRoles(req, res);
					}
				} else {
					res.setStatus(403);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
				
			case "Reimbursement":
				if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("Loggedin")) {
					if (req.getMethod().equals("GET")) {
						if (portions.length == 2) {
							int reimbId = Integer.parseInt(portions[1]);
							rc.getReimbursement(res, reimbId);
						}else if (portions.length == 1) {
							rc.getAllReimbursement(res);
						}else if(portions[1].equals("findallreimbursementstatus")){
							rc.findAllReimbursementStatus(res);
						}else if(portions[1].equals("findallreimbursementtype")) {
							rc.findAllReimbursementType(res);
						}else if(portions[1].equals(System.currentTimeMillis())) {
							int reimbId = Integer.parseInt(portions[1]);
							//COULD ALSO DO REIMBRESOLVER INSTEAD HERE TO DO EMPLOYEE PAST REQUESTS
							Timestamp reimbResolved = new Timestamp(System.currentTimeMillis());
							rc.getPastReimbursement(res, reimbId, reimbResolved);
						}else if(portions[1].equals("findreimbursementbyauthor")) {
							int reimbAuthor = Integer.parseInt(portions[2]);
							rc.findReimbursementByAuthor(res, reimbAuthor);
						}
					}
					if(req.getMethod().equals("POST")) {
							rc.addReimbursement(req, res);
						}
					if (req.getMethod().equals("PUT")) {
						if(portions[1].equals("reimbursementStatus")){
							int reimbStatusId = Integer.parseInt(portions[2]);
							rc.updateReimbursementStatus(req, res, reimbStatusId);
						}
				
				} else {
					res.setStatus(403);
					res.getWriter().println("You must be logged in to do that!");
				}
				}
				break;
				
			case "login":
				lc.login(req, res);
				break;
				
			case "logout":
				lc.logout(req, res);
				break;
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.getWriter().print("The user id you provided is not an integer");
			res.setStatus(400);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}