package com.delta.charter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delta.charter.domain.CharterUser;
import com.delta.charter.domain.UserRole;
import com.delta.charter.repository.RoleRepository;
import com.delta.charter.repository.UserJdbcRepository;
import com.delta.charter.repository.UserJpaRepository;
import com.delta.charter.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	private UserJdbcRepository userJdbcRepository;

	@Override
	public CharterUser createUser(CharterUser charterUser) {
		userJdbcRepository.createUser(charterUser);
		return charterUser;
	}

	@Override
	public CharterUser findByusername(String username) {
		CharterUser user = userJpaRepository.findByusername(username);
		return user;
	}

	@Override
	public List<CharterUser> findAll() {
		List<CharterUser> userList = userJpaRepository.findAll();
		return userList;
	}
	
	
	
	
	
	
	/*@Transactional
	public CharterUser createUser(CharterUser charterUser){
		CharterUser curruser = userRepository.findByusername(charterUser.getUsername());
		
		if(curruser != null){
			LOG.info("username already exist",curruser.getUsername());
		} else {
			userRepository.save(charterUser);
		}
		return charterUser;
	}*/

	

}
