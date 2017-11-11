package com.delta.charter.controller;

import java.nio.charset.Charset;
import java.util.Base64;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.delta.charter.service.UserService;
import com.delta.charter.jwt.redis.JwtUtil;
import com.delta.charter.jwt.redis.RedisUtil;



@RestController
public class LoginController {
	
	
    
    @Value("${jwt.secret}")
    private String signingKey;

    @Value("${jwt.expiration}")
    private Long expiration;
    
	@Autowired
	private UserService userService;
	
	@RequestMapping("/token")
	public Map<String, String> token(HttpSession session, HttpServletRequest request) {
	 
		String remoteHost = request.getRemoteHost();
		int portNumber = request.getRemotePort();
		System.out.println("Session created:->"+request.getRemoteAddr() +  session.getId());
	//	return Collections.singletonMap("token", session.getId());
		String user =  JwtUtil.getrequestusernamefromheader(request);
		return Collections.singletonMap("token", JwtUtil.generateToken(signingKey, user));
	}
	
	
	
	@RequestMapping("/checkSession")
	public ResponseEntity checkSession(HttpServletRequest httpServletRequest) {
		
		String userAgent = httpServletRequest .getHeader("x-auth-token");
		if(userAgent.isEmpty()){
			return new ResponseEntity("Session Invalid!", HttpStatus.FORBIDDEN);
		}
		
		try{
		String test = JwtUtil.parseToken(httpServletRequest, userAgent, signingKey);
		if(test==null){
			return new ResponseEntity("Session Active!", HttpStatus.FORBIDDEN);
		}
		}
		catch(Exception e){
			return new ResponseEntity("Session Active!", HttpStatus.FORBIDDEN);
		}
		System.out.println("Session created:->" + userAgent);
		return new ResponseEntity("Session Active!", HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/user/logout", method=RequestMethod.POST)
	public ResponseEntity logout(HttpSession session, HttpServletRequest request){
		 String token = JwtUtil.gettoken(request);
		 JwtUtil.invalidateRelatedTokens(request,token,signingKey);
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