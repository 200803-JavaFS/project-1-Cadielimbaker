package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.services.LoginService;
import com.revature.services.UsersService;

public class LoginController {

	private static LoginService ls = new LoginService();
	private static UsersService us = new UsersService();
	private static ObjectMapper om = new ObjectMapper();


		public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
			BufferedReader reader = req.getReader();

			StringBuilder sb = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = new String(sb);

					LoginDTO l = om.readValue(body, LoginDTO.class);
					
					if (ls.login(l)) {
						HttpSession ses = req.getSession();
						ses.setAttribute("user", l);
						ses.setAttribute("loggedin", true);
						ses.setAttribute("User_Role_Id", us.selectByUsername(l.userName).getUserRoleId().getUserRole());
						res.setStatus(200);
						System.out.println("in LC se = " + ses);
						String json = om.writeValueAsString((Integer)ses.getAttribute("user_role_id"));
						res.getWriter().println(json);
					} else {
						HttpSession ses = req.getSession(false);
						if (ses != null) {
						ses.invalidate();
						}
						res.setStatus(401);
						res.getWriter().println("Login failed");
					}			
			}

		public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
			HttpSession ses = req.getSession(false);

			if (ses != null) {
				LoginDTO l = (LoginDTO) ses.getAttribute("user");
				ses.invalidate();
				res.setStatus(200);
				res.getWriter().println(l.userName + " has logged out successfully");
			} else {
				res.setStatus(400);
				res.getWriter().println("You must be logged in to logout!");
			}
		}
	}

		
//			if (req.getMethod().equals("POST")) {
//			// this is how a login should generally be handled. Sending credentials in the
//			// body of a POST request.
//			BufferedReader reader = req.getReader();
//
//			StringBuilder sb = new StringBuilder();
//
//			String line = reader.readLine();
//
//			while (line != null) {
//				sb.append(line);
//				line = reader.readLine();
//			}
//
//			String body = new String(sb);
//
//			LoginDTO l = om.readValue(body, LoginDTO.class);
//
//			if (ls.login(l)) {
//				HttpSession ses = req.getSession();
//				ses.setAttribute("user", l);
//				ses.setAttribute("loggedin", true);
//				ses.setAttribute("User_Role_ID", us.selectByUsername(l.userName).getUserRoleId());
//				res.setStatus(200);
//				String json = om.writeValueAsString(ses.getAttribute("User_Role_ID"));
//				res.getWriter().println(json);
//				//res.getWriter().println("Login Successful");
//			} else {
//				HttpSession ses = req.getSession(false);
//				if (ses != null) {
//					ses.invalidate();
//				}
//				res.setStatus(401);
//				res.getWriter().println("Login failed");
//			}
//
//		}
//	}
//
//	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		HttpSession ses = req.getSession(false);
//
//		if (ses != null) {
//			LoginDTO l = (LoginDTO) ses.getAttribute("user");
//			ses.invalidate();
//			res.setStatus(200);
//			res.getWriter().println(l.userName + " has logged out successfully");
//		} else {
//			res.setStatus(400);
//			res.getWriter().println("You must be logged in to logout!");
//		}
//	}
//
//}
