package com.delta.charter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.delta.charter.domain.CharterUser;


public interface UserJpaRepository extends JpaRepository<CharterUser, Long> {
	List<CharterUser> findAll();
	CharterUser findByusername(String username);
//  save(UserRole role);

	
	/*@Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
	 @Query("INSERT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
	    public List<Person> find(@Param("lastName") String lastName);
	}*/
}
