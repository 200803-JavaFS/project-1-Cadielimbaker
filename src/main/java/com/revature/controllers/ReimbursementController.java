package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.AddReimbursementDTO;
import com.revature.models.LoginDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.services.ReimbursementService;
import com.revature.models.Users;
import com.revature.services.UsersService;

public class ReimbursementController {


	private static ReimbursementService rs = new ReimbursementService();
	private static UsersService us = new UsersService();
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
	
	public void getPastReimbursement(HttpServletResponse res, int reimbId, Timestamp reimbResolved) throws IOException {
		Reimbursement r = rs.selectByReimbId(reimbId);
		Reimbursement rr = rs.selectByReimbResolved(reimbResolved);
		if(r == null || rr != null) {
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
	
	public void findAllReimbursementStatus(HttpServletResponse res, int reimbStatusId) throws IOException {
		List<Reimbursement> all = rs.findAllReimbursementStatus(reimbStatusId);
		String json = om.writeValueAsString(all);
		res.getWriter().println(json);
		res.setStatus(200);
	}
	
	public void findAllReimbursementType(HttpServletResponse res, int reimbTypeId) throws IOException {
		List<Reimbursement> all = rs.findAllReimbursementType(reimbTypeId);
		res.getWriter().println(om.writeValueAsString(all));
		res.setStatus(200);
	}
	
	public void findReimbursementByAuthor(HttpServletResponse res, int reimbAuthor) throws IOException {
		List<Reimbursement> all = rs.findReimbursementByAuthor(reimbAuthor);
		res.getWriter().println(om.writeValueAsString(all));
		res.setStatus(200);
	}

	public void findReimbursementByResolver(HttpServletResponse res, int reimbResolver) throws IOException {
		List<Reimbursement> all = rs.findReimbursementByReimbResolver(reimbResolver);
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
		AddReimbursementDTO ardto = om.readValue(body, AddReimbursementDTO.class);
		Integer usersId = (Integer) req.getSession().getAttribute("usersId");
		
		if(rs.addReimbursement(ardto,usersId.intValue())) {
			res.setStatus(200);
		}
		else {
			res.setStatus(401);
		}
	}
	
//	//HELLLLLLPPPPPPP
//	public void updateReimbursementStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
////		ReimbursementStatus rstatus = rs.selectByReimbStatusId(reimbStatusId);
////		res.setStatus(200);
////		String json = om.writeValueAsString(rstatus);
////		res.getWriter().println(json);
////		
//		BufferedReader reader = req.getReader();
//		StringBuilder s = new StringBuilder();
//		String line = reader.readLine();
//
//		if(line !=null) {
//		s.append(line);
//		line = reader.readLine();
//	
//		String body = new String(s);
//		ReimbursementDTO rdto = om.readValue(body, ReimbursementDTO.class);
//		HttpSession ses = req.getSession();
//		Integer usersId = (Integer)ses.getAttribute("usersId");
//		
//		if(rs.updateReimbursement(rdto, usersId.intValue())) {
//		
//		res.setStatus(201);
//		res.getWriter().println("The Reimbursement Status was updated!");
//		
//		}else {
//			res.setStatus(403);
//		}
//		}
//	}

	
	public void addReimbursementStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		System.out.println(body);
		
		ReimbursementStatus reimbStatus = om.readValue(body, ReimbursementStatus.class);
		
		System.out.println(reimbStatus);
		
		if (rs.addReimbursementStatus(reimbStatus) != null) {
			res.setStatus(201);
			res.getWriter().println("A Reimbursement Status was added!");
		} else {
			res.setStatus(403);
		}
}

	public void addReimbursementType(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		System.out.println(body);
		
		ReimbursementType reimbType = om.readValue(body, ReimbursementType.class);
		
		System.out.println(reimbType);
		
		if (rs.addReimbursementType(reimbType) != null) {
			res.setStatus(201);
			res.getWriter().println("A Reimbursement Type was added!");
		} else {
			res.setStatus(403);
		}
}
	public void updateR(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		
		BufferedReader reader = req.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		AddReimbursementDTO ardto = om.readValue(body, AddReimbursementDTO.class);
		
		if(rs.updateReimbursement(ardto)) {
			res.setStatus(201);
			res.getWriter().println("Status Changed");
		}
		else {
			res.setStatus(403);
		}
	}

public void findByUname(HttpServletRequest req, HttpServletResponse res) throws IOException{
	HttpSession ses = req.getSession(false);
	LoginDTO l = (LoginDTO) ses.getAttribute("user");
	System.out.println(l.userName);
    Users u = us.selectByUsername(l.userName);
    
    List<Reimbursement> rList = rs.findReimbursementByAuthor(u.getUsersId());
    List<AddReimbursementDTO> ardtoList = new ArrayList<>();
    res.setStatus(200);
    AddReimbursementDTO ardto = new AddReimbursementDTO();
    for (Reimbursement r: rList) {
    	ardto.reimbAuthor = Integer.toString(r.getReimbAuthor().getUsersId());
		ardto.reimbAmount = r.getReimbAmount();
		ardto.reimbId = r.getReimbId();
		if(r.getReimbResolver()!= null) {
			ardto.reimbResolver = Integer.toString(r.getReimbResolver().getUsersId());
		}
		else {
			ardto.reimbResolver = null;
		}
		ardto.reimbDescription = r.getReimbDescription();
		ardto.reimbStatusId = (r.getReimbStatusId().getReimbStatusId());
		ardto.reimbTypeId = (r.getReimbTypeId().getReimbTypeId());
		ardtoList.add(ardto);
	}
	System.out.println(ardtoList.toString());
	
	
	try {
		res.getWriter().println(om.writeValueAsString(ardtoList));
	} catch (JsonProcessingException e) {
			res.setStatus(400);
		e.printStackTrace();
	} catch (IOException e) {
			res.setStatus(401);
		e.printStackTrace();
	}
    }
}


