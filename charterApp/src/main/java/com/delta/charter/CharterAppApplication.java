package com.delta.charter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.delta.charter.config.SecurityUtility;
import com.delta.charter.domain.CharterUser;
import com.delta.charter.service.RoleService;
import com.delta.charter.service.UserService;


@SpringBootApplication
//@SpringBootApplication(scanBasePackages= {"com.delivery.service","com.delivery.request"})c
//@SpringBootApplication(scanBasePackages= {"om.delta.charter.service"})
@ComponentScan( basePackages = { "com.delta.charter" })
public class CharterAppApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	

	public static void main(String[] args) {
		SpringApplication.run(CharterAppApplication.class, args);
	}
	@Value("${environmentname.file}")
	private String env;

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Profile:---->" + env);
		
		CharterUser user = new CharterUser();
	    user.setUsername("m");;
		user.setFirstname("Alpesh");
		user.setLastname("Sanghani");
		user.setMobilenumber(614440023);
		user.setOfficenumber(614440023);
		user.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user.setRoleid(0);
	//	userService.createUser(user);
		/*	//user.setPassword("alpesh");
		jdbcTemplate.update("INSERT INTO charter_user_Jdbc (username,firstname,lastname,roleId,mobilenumber,officenumber,password) "
				+ "VALUES(?,?,?,?,?,?,?)",
				new Object[] { user.getUsername(),user.getFirstname(),user.getLastname(),user.getRoleid(),user.getMobilenumber(),user.getOfficenumber(),user.getPassword()});*/
		//userService.createUser(chuser);
		/*UserRole url1 = new UserRole();
		url1.setRoleId(1);
		url1.setRoleName("chrt");
		roleService.createRole(url1);*/
		//jdbcTemplate.update("INSERT INTO test (uname) VALUES(?)",new Object[] { "xx"});
		    
	}   
}
