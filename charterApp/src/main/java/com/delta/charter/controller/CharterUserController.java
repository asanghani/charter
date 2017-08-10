package com.delta.charter.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.delta.charter.config.SecurityUtility;
import com.delta.charter.domain.CharterUser;
import com.delta.charter.service.UserService;


@RestController
@RequestMapping("/charteruser")
public class CharterUserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public CharterUser addUserPost(@RequestBody CharterUser charterUser){
		charterUser.setPassword(SecurityUtility.passwordEncoder().encode(charterUser.getPassword()));
		return userService.createUser(charterUser);
	}
	
	@RequestMapping(value="userList")
	public List<CharterUser> getUserList(){
		return userService.findAll();
		
	}
	
	@RequestMapping(value="/{username}")
	public CharterUser getUser(@PathVariable("username") String username){
		CharterUser cuser = userService.findByusername(username);
		return cuser;
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public CharterUser updateUserPost(@RequestBody CharterUser charterUser){
		return userService.createUser(charterUser);
	}

}
