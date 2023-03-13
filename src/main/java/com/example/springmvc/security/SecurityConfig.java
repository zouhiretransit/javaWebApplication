package com.example.springmvc.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource datasource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder=passwordEncoder();
		System.out.println("*****************************");
		System.out.println(passwordEncoder.encode("1234"));
		System.out.println("*****************************");
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select username , password , active from users where username=?")
		.authoritiesByUsernameQuery("select username, role from users_roles where username=?")
		.passwordEncoder(passwordEncoder);
		
		  /*auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
		  auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("1234")).roles("USER");
		 auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("1234")).roles("ADMIN","USER");
		 */
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**","/images/**").permitAll();
		
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/login","/webjars/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		
		//http.authorizeRequests()
        //.antMatchers("/h2-console/**").permitAll();

        http.csrf().disable();
        //http.headers().frameOptions().disable();
	}
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
