package com.example.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	
	@GetMapping("/403")
	public String index(Model model) {
		model.addAttribute("code",403);
  	  	model.addAttribute("message","not have access");
		return "error";
	}
	
}
