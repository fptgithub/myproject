package com.example.configuration;

import java.util.Iterator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecutityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/home/**","/account/**").permitAll()
		.antMatchers("/img/**",
        		   "/admin_js/plugins/**",
        		   "/js/**",
        		   "/css/**",
        		   "/fonts/**",
        		   "/admin_js/**",
        		   "/order/detail",
        		   "/order/detail/check").permitAll()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/card/**").hasAnyRole("ADMIN","USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/account/login")
		.defaultSuccessUrl("/home")
		.and().logout()
		.and()
		.csrf().disable()
		.exceptionHandling().accessDeniedPage("/403");
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web
        .ignoring()
           .antMatchers("/static/**");
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
