package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.interceptor.HomeInterceptor;

@Component
public class WebMvcConfig implements WebMvcConfigurer{
	@Autowired
	HomeInterceptor homeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(homeInterceptor)
		.addPathPatterns("/home/**","/card/**","/account/**","/order/**");
	}
	
}
