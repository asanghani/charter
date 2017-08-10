package com.delta.charter.repository;

import org.springframework.stereotype.Component;

import com.delta.charter.domain.CharterUser;

@Component
public interface UserJdbcRepository {
	public void createUser(CharterUser user);


}
