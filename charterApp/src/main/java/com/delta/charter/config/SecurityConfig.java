package com.delta.charter.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.delta.charter.service.UserSecurityService;
import com.delta.charter.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
	        return new JdbcTemplate(dataSource);
	    }

	private BCryptPasswordEncoder passwordEncoder(){
		return SecurityUtility.passwordEncoder();
	}
	
	@Autowired
	DataSource dataSource;
	
	
	//*****************************************Method is use for external web-server
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().httpBasic().and().authorizeRequests()
		.antMatchers("/admin").access("hasRole('ROLE_ADMIN')") 
		.antMatchers("/checkSession").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/charteruser/*").permitAll() 
		.anyRequest().authenticated(); 
	}*/
	
	
	//******************************************Method is use for Internal web-server
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	System.out.println("test********************************************************");
	http.csrf().disable().cors().disable().httpBasic().and().authorizeRequests()
	
	.antMatchers("/index.html/checkSession").access("hasRole('ROLE_ADMIN')") 
	.antMatchers("/token").permitAll() 
	.antMatchers("/checkSession").access("hasRole('ROLE_ADMIN')")
	.antMatchers("/charteruser/*").permitAll()

	.antMatchers(PUBLIC_MATCHERS).permitAll()
	//.anyRequest().authenticated();   
	.and()
	.formLogin()
	.loginPage("/index.html")
	.permitAll();
	/*.and()
	.logout()
    .logoutUrl("/")
    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
    .deleteCookies("JSESSIONID")
    .permitAll(); */
	}
	
	private static final String[] PUBLIC_MATCHERS = {
		"/fontawesome-webfont.*.*",	
		"/**/*.js"
		/*"/resources/**",
		"/css/**",
		"/image/**",*/
    };
			
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
				"select username,password,enabled,firstname,lastname,mobilenumber,officenumber from charter_user where username=?")
		.authoritiesByUsernameQuery(
			    "select username, role from user_roles where username=?");
	}	
	/* @Override
	    public void configure(WebSecurity webSecurity) throws Exception
	    {
	        webSecurity
	            .ignoring()
	                // ... whatever is here is ignored by All of Spring Security
	    }*/
	@Bean
	public HttpSessionStrategy httpSessionStrategy(){
		return new HeaderHttpSessionStrategy();
	}
	
}






//------------------------------------------------------------------------------------------------------------------
/*private static final String[] PUBLIC_MATCHERS = {
"/css/**",
"/js/**",
"/image/**",
"/team/**",
"/charteruser/**",
//Thymleaf test
"/login",

};*/

/*private static final String[] PUBLIC_MATCHERS = {
//"/charteruser/userList"
};*/
//----------------------------------------------------------------------------------
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	private Environment env;
//	
//	@Autowired
//	private UserSecurityService userSecurityService;
//	
//	@Bean
//	public JdbcTemplate jdbcTemplate(DataSource dataSource){
//	        return new JdbcTemplate(dataSource);
//	    }
//
//	private BCryptPasswordEncoder passwordEncoder(){
//		return SecurityUtility.passwordEncoder();
//	}
//	
//	@Autowired
//	DataSource dataSource;
//	
//	/*private static final String[] PUBLIC_MATCHERS = {
//			"/css/**",
//			"/js/**",
//			"/image/**",
//			"/team/**",
//			"/charteruser/**",
//			//Thymleaf test
//			"/login",
//			
//	};*/
//	
//	private static final String[] PUBLIC_MATCHERS = {
//			/*"/css/**",
//			"/js/**",
//			"/image/**",
//			"/charteruser/**",*/
//			/*"/token",
//			"/checkSession",
//			"/user/logout",*/
//			"/*/**"
//	};
//	
//	/*@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().cors().disable().httpBasic().and()
//		.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
//	}
//	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
//	}*/
//	/*@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().and().cors().disable().httpBasic().and().authorizeRequests()
//			.antMatchers("/admin/**").access("hasRole('ROLE_USER')")
//			.and()
//			    .formLogin().loginPage("/index1").failureUrl("/index1?error")
//			    .usernameParameter("username").passwordParameter("password")
//			.and()
//			    .csrf();
//		}*/
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().cors().disable().httpBasic().and().authorizeRequests()
//		.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated().and()
//        .formLogin()
//        .loginPage("/index.html")
//        .permitAll();; 
////		.antMatchers("/admin").access("hasRole('ROLE_ADMIN')") //thymleaf test-only with ADMIN role user can access this page
////		.antMatchers("/welcome").access("hasRole('ROLE_USER')")//thymleaf test-only with USER role user can access this page
////		.antMatchers("/charterAdmin/**").access("hasRole('ROLE_ADMIN')") //For angular user to have ADMIN role only
////		.anyRequest().authenticated();     //ALL OTHER THYMLEAF OR ANGULAR USER CAN ACCESS OTHER PAGES(ALL User role from anuglar or thyleaf allow )
//		
//	}
//	
//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		
//	  auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery(
//				"select username,password,enabled,firstname,lastname,mobilenumber,officenumber from charter_user where username=?")
//		.authoritiesByUsernameQuery(
//			    "select username, role from user_roles where username=?");
//	}	
//	
//	@Bean
//	public HttpSessionStrategy httpSessionStrategy(){
//		return new HeaderHttpSessionStrategy();
//	}
//}
