package com.example.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.DashBoardDTO;
import com.example.servic.GeneralServic;
import com.example.servic.UserServic;

@Controller
@RequestMapping("/admin")
public class DashBoardController {

	@Autowired
	GeneralServic generalServic;
	
	@GetMapping()
	public String dashBoard() {
		return "admin/dashboard";
	}
	
	@ModelAttribute("dashboardData")
	public DashBoardDTO generalDashBoard() {
		return generalServic.dashBoard();
	}
}
