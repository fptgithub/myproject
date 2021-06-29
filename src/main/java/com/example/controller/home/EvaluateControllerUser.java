package com.example.controller.home;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Evaluate;
import com.example.servic.EvaluateServic;

@Controller
@RequestMapping("/evaluate")
public class EvaluateControllerUser {
	
	@Autowired
	EvaluateServic evaluateServic;
	
	@GetMapping("/add/{productId}")
	public String loginForEvaluate(@PathVariable("productId") Optional<Integer> productId
			,@AuthenticationPrincipal User user) {
		return "redirect:/home/detail/"+String.valueOf(productId.get());
	}
	
	@PostMapping("/add/{productId}")
	public String createEvaluate(@PathVariable("productId") Optional<Integer> productId
			,@AuthenticationPrincipal User user
			,Evaluate evaluate) {
		evaluateServic.createEvaluate(user.getUsername(), evaluate, productId);
		return "redirect:/home/detail/"+String.valueOf(productId.get());
	}
	
	@ResponseBody
	@GetMapping("/delete/{EvaluateId}")
	public String deleteEvaluate(@PathVariable("EvaluateId") Integer evaluateId) {
		evaluateServic.deleteEvaluate(evaluateId);
		return "success";
	}
	
}
