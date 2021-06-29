package com.example.controller.home;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.OderDTO;
import com.example.entity.Oder;
import com.example.servic.OrderServic;

@Controller
@RequestMapping("/order")
public class OderControllerUser {

	@Autowired
	OrderServic orderServic;
	
	@PostMapping("/create")
	public String createOrder(@Valid @ModelAttribute("orderdto") OderDTO dto,BindingResult result
			,@AuthenticationPrincipal User user
			,Model model
			,HttpServletRequest rs) {
		if(result.hasErrors()) {
			return "forward:/card";
		}
		Oder oder = orderServic.create(user.getUsername(), dto);
		if(oder==null) {
			model.addAttribute("message", "card is empty");
			return "forward:/card";
		}
		return "redirect:/order/detail/check?orderIdStr="+oder.getOrderIdStr();
	}
	
	@GetMapping("/detail")
	public String detail() {
		return "home/checkorder";
	}
	
	
	@GetMapping("/detail/check")
	public String orderDetail(@RequestParam("orderIdStr") String orderIdStr 
			,Model model){
		Oder order = orderServic.findByOrderIdStr(orderIdStr);
		model.addAttribute("order", order);
		model.addAttribute("orderDetail", orderServic.findByOrderId(order.getId()));
		return "home/order";
	}
}
