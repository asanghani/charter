package com.delta.charter.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.delta.charter.domain.CharterUser;
import com.delta.charter.repository.UserJdbcRepository;

@Component
public class UserJdbcRepositoryImpl implements UserJdbcRepository  {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public void createUser(CharterUser user) {
		jdbcTemplate.update("INSERT INTO charter_user (username,firstname,lastname,roleId,mobilenumber,officenumber,password) "
				+ "VALUES(?,?,?,?,?,?,?)",
				new Object[] { user.getUsername(),user.getFirstname(),user.getLastname(),user.getRoleid(),user.getMobilenumber(),user.getOfficenumber(),user.getPassword()});
	}
	
	public void test1(){
			jdbcTemplate.update("INSERT INTO test (uname) VALUES(?)",new Object[] { "xx"});
		}

}
