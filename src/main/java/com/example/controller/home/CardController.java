package com.example.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.OderDTO;
import com.example.entity.Card;
import com.example.servic.CardServic;

@Controller
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	CardServic cardServic;
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("orderdto", new OderDTO());
		return "home/card";
	}
	
	@PostMapping()
	public String indexPost() {
		return "home/card";
	}
	
	@ResponseBody
	@GetMapping("/add/{productId}")
	public Card add(@AuthenticationPrincipal User user,@PathVariable(name = "productId") Integer productId
			,@RequestParam(value = "quantity",required = false) Integer quantity) {
		return cardServic.addTocard(user, productId,quantity);
	}
	
	@ResponseBody
	@GetMapping("/reduce/{productId}")
	public Card reduce(@AuthenticationPrincipal User user,@PathVariable(name = "productId") Integer productId) {
		return cardServic.reduce(user, productId);
	}
	
	@ResponseBody
	@GetMapping("/delete/{productId}")
	public String delete(@AuthenticationPrincipal User user,@PathVariable(name = "productId") Integer productId) {
		cardServic.delete(user, productId);
		return "";
	}
	
	@ModelAttribute(name = "products")
	public List<Card> getCardByUser(@AuthenticationPrincipal User user){
		return cardServic.findAllByUsername(user.getUsername());
	}
}
