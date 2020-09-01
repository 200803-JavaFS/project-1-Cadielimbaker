package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UsersController;
import com.revature.controllers.UsersController;
import com.revature.models.Users;

public class MasterServlet extends HttpServlet {

	private static UsersController uc = new UsersController();
	private static LoginController lc = new LoginController();
	private static ReimbursementController rc = new ReimbursementController();

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
						} else if (portions.length == 1) {
							rc.getAllReimbursement(res);
						}
					} else if (req.getMethod().equals("POST")) {
						rc.addReimbursement(req, res);
					}
				
				} else {
					res.setStatus(403);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
				
			case "Login":
				lc.login(req, res);
				break;
				
			case "Logout":
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

}