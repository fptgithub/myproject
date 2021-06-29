package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.servic.UserServic;

@Service
public class MyUserDetailsServic implements UserDetailsService{
	
	@Autowired
	UserServic userServic;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userServic.getUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found with username : "+username));
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRoleArr())
				.build();
	}
}
