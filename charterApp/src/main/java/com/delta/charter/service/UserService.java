package com.delta.charter.service;


import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.delta.charter.domain.CharterUser;


//@Service
public interface UserService{
	
	CharterUser createUser(CharterUser charterUser);

	CharterUser findByusername(String username);
	
	List<CharterUser> findAll();
	
}
