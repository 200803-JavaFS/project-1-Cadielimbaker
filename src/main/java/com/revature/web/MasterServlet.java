package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println(portions.length);
		System.out.println((portions[0]));
		
		HttpSession ses = req.getSession(false);
		System.out.println("@MS ses = " + ses);
		if (ses != null && (boolean) ses.getAttribute("loggedin")==true) {
		try {
			switch (portions[0]) {
				
				case "reimbursement":
					if(req.getMethod().equals("GET")) {
						rc.getAllReimbursement(res);
					}
					break;				
				case "reimbursementsbystatus":
					int rsId = Integer.parseInt(portions[1]);
					rc.findAllReimbursementStatus(res, rsId);
					break;
				case "reimbursementsbyemployee":
					int empId = Integer.parseInt(portions[1]);
					rc.findReimbursementByAuthor(res, empId);
					break;
				case "empreimbursementsearch":
					System.out.println("@case emprsearch in MS");
					rc.findByUname(req, res);
					break;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.setStatus(400);
		}
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
				
			case "addR":
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
		
		if (URI.equals("changestatus")) {
			rc.updateR(req, res);	
		}
		

	}
}

