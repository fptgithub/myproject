package com.example.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.PaginationRequestDTO;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.servic.UserServic;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	UserServic userServic;
	
	@GetMapping("/table")
	public String table(@ModelAttribute(name = "pageRequest")PaginationRequestDTO pRequest
			,Model model) {
		Page<User> p = null;
		if(pRequest.getSearch()!=null) {
			p = userServic.search(pRequest.getSearch(),pRequest,10);
		}else {
		 p = userServic.paginationAndSort(pRequest,10);
		}
		model.addAttribute("pageableUser",p);
		model.addAttribute("pagination", userServic.caculatorPage(p));
		return "admin/table/user";
	}
}
