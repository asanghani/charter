package com.delta.charter.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.delta.charter.service.UserService;



@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/token")
	public Map<String, String> token(HttpSession session, HttpServletRequest request) {
	//	System.out.println(request.getRemoteHost());
		
		String remoteHost = request.getRemoteHost();
		int portNumber = request.getRemotePort();
		
		//System.out.println(remoteHost+":"+portNumber);
		//System.out.println(request.getRemoteAddr());
		System.out.println("Session created:->"+request.getRemoteAddr() +  session.getId());
		return Collections.singletonMap("token", session.getId());
	}
	
	
	
	@RequestMapping("/checkSession")
	public ResponseEntity checkSession() {
		return new ResponseEntity("Session Active!", HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="/user/logout", method=RequestMethod.POST)
	public ResponseEntity logout(HttpSession session, HttpServletRequest request){
		 
		 session = request.getSession();
		 session.invalidate();
		 session = request.getSession(false);
		if(session==null || !request.isRequestedSessionIdValid() )
		{
			System.out.println("Session not valid");

		}
		 SecurityContextHolder.clearContext();
		
		System.out.println("Session ended:->"+ request.getRemoteAddr());	
		return new ResponseEntity("Logout Successfully!", HttpStatus.OK);
	}

}