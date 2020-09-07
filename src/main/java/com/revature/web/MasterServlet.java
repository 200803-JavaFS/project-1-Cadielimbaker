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
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		res.setContentType("application/json");
		
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/project1/", "");

		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));
		
		if ((portions[0]).equals("reimbursement")) {
			if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
				if (portions.length == 2) {
					int reimbId = Integer.parseInt(portions[1]);
					rc.getReimbursement(res, reimbId);
				}else if (req.getMethod().equals("GET")) {
					rc.getAllReimbursement(res);
				}else if(portions.length == 3 && portions[1].equals("finallreimbursementstatus")){
					int reimbStatusId = Integer.parseInt(portions[2]);
					rc.findAllReimbursementStatus(res, reimbStatusId);
//				}else if(portions.length == 3 && portions[1].equals("findallreimbursementtype")) {
//					int reimbTypeId = Integer.parseInt(portions[2]);
//					rc.findAllReimbursementType(res, reimbTypeId);
//				}else if(portions.length == 3 && portions[1].equals("findreimbursementbyresolver")) {
//					int reimbResolver = Integer.parseInt(portions[2]);
//					rc.findReimbursementByResolver(res, reimbResolver);
//				}else if(portions.length == 3 && portions[1].equals("findreimbursementbyauthor")) {
//					int reimbAuthor = Integer.parseInt(portions[2]);
//					rc.findReimbursementByAuthor(res, reimbAuthor);
				}
			} else {
				res.setStatus(403);
				res.getWriter().println("You must be logged in to do that!");
			}
		} else {
		res.setStatus(400);
		}	 
	}
		

	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
		
		res.setContentType("application/json");
		
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/project1/", "");

		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));
		
		try {
			switch (portions[0]) {
			
			case "login":
				//Logger.info("@loggedin");
				lc.login(req, res);	
				break;
				
			case "reimbursement":
				//log.info("@addreimbursement");
				rc.addReimbursement(req, res);
				break;
				
			case "logout":
				//log.info("@logout");
				lc.logout(req, res);
				break;
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.getWriter().print("You are requesting a page that does not exist.");
			res.setStatus(400);
		}
	}
	
	@Override
	protected void doPut (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
		
		/*reimbStatusId
		 * status codes: 1==pending, 2==approved, 3==denied
		 */
		
		res.setContentType("application/json");
		
		res.setStatus(400);

		final String URI = req.getRequestURI().replace("/project1/", "");

		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));
		
		if (URI.equals("reimbursement")) {
			//log.info("@updatereimbursement")
				rc.updateReimbursementStatus(req, res);	
		}
		

	}
}

