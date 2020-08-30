package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.services.ReimbursementService;

public class ReimbursementController {


	private static ReimbursementService rs = new ReimbursementService();
	private static ObjectMapper om = new ObjectMapper();
	
	public void getReimbursement(HttpServletResponse res, int reimbId) throws IOException {
		Reimbursement r = rs.selectByReimbId(reimbId);
		if(r == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(r);
			res.getWriter().println(json);
		}
		
	}
	
	public void getAllReimbursement(HttpServletResponse res) throws IOException {
		List<Reimbursement> all = rs.findAllReimbursement();
		res.getWriter().println(om.writeValueAsString(all));
		res.setStatus(200);
	}

	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		System.out.println(body);
		
		Reimbursement r = om.readValue(body, Reimbursement.class);
		
		System.out.println(r);
		
		if (rs.addReimbursement(r)) {
			res.setStatus(201);
			res.getWriter().println("A Reimbursement was added!");
		} else {
			res.setStatus(403);
		}
}
	public void updateReimbursementStatus(HttpServletRequest req, HttpServletResponse res, int reimbStatusId) throws IOException {
		ReimbursementStatus rstatus = rs.selectByReimbStatusId(reimbStatusId);
		res.setStatus(200);
		String json = om.writeValueAsString(rstatus);
		res.getWriter().println(json);
		
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();

		if(line !=null) {
		s.append(line);
		line = reader.readLine();
	
		String body = new String(s);
		System.out.println(body);
		String reimbStatus = om.updateValue(body, ReimbursementStatus.class);
		System.out.println(reimbStatus);
		
		rs.updateReimbursementStatus(reimbStatus, reimbStatusId); 
		res.setStatus(201);
		res.getWriter().println("The Reimbursement Status was updated!");
		
		}else {
			res.setStatus(403);
		}
	}

	public void updateReimbursementType(HttpServletRequest req, HttpServletResponse res, int reimbTypeId) throws IOException {
		ReimbursementType rtype = rs.selectByReimbTypeId(reimbTypeId);
		res.setStatus(200);
		String json = om.writeValueAsString(rtype);
		res.getWriter().println(json);
		
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		
		if(line != null) {
		s.append(line);
		line = reader.readLine();
		
		String body = new String(s);
		System.out.println(body);
		String reimbType = om.updateValue(body, ReimbursementType.class);
		System.out.println(reimbType);
		
		rs.updateReimbursementType(reimbType, reimbTypeId);
		res.getWriter().println("The Reimbursement Type was updated!");
		
	}else {
		res.setStatus(403);
	}
}
}

