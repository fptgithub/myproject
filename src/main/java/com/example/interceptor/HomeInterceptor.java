package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.servic.CategoryServic;
import com.example.servic.ProductServic;

@Component
public class HomeInterceptor implements HandlerInterceptor{
	@Autowired
	CategoryServic categoryServic;
	@Autowired
	ProductServic productServic;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("categoryByCustom", categoryServic.CategorysByCustomType());
		request.setAttribute("dealofweek", productServic.dealOfWeek());
	}
}
