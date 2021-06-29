package com.example.controller.home;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.PaginationRequestDTO;
import com.example.entity.Product;
import com.example.servic.EvaluateServic;
import com.example.servic.ProductServic;

@Controller
@RequestMapping("/home")
public class ProductController {

	@Autowired
	ProductServic productServic;
	@Autowired
	EvaluateServic evaluateServic;

	@GetMapping("/search")
	public String search(@RequestParam("name") String name,@ModelAttribute(name = "pageRequest")PaginationRequestDTO page,Model model) {
		Page<Product> p = productServic.search(name, page, 9);
		model.addAttribute("products", p);
		model.addAttribute("name", name);
		model.addAttribute("pagination", productServic.caculatorPage(p));
		return "home/category";
	}
	
	@GetMapping("/{customSlug}")
	public String customTypePageDetail(@PathVariable(name = "customSlug") String customSlug, Model model,
			@ModelAttribute(name = "pageRequest") PaginationRequestDTO pRequest) {
		Page<Product> p = productServic.findAllProductByCustomtypeAndPageable(customSlug, pRequest, 9);
		model.addAttribute("products", p);
		model.addAttribute("pagination", productServic.caculatorPage(p));
		model.addAttribute("customSlug", customSlug);
		return "home/category";
	}

	@GetMapping("/{customSlug}/{categorySlug}")
	public String categoryPageDetail(@PathVariable(name = "customSlug") String customSlug,
			@PathVariable(name = "categorySlug") String categorySlug, Model model,
			@ModelAttribute(name = "pageRequest") PaginationRequestDTO pRequest) {
		Page<Product> p = productServic.findAllProductByCategoryAndPageable(customSlug, categorySlug, pRequest, 9);
		model.addAttribute("products", p);
		model.addAttribute("pagination", productServic.caculatorPage(p));
		model.addAttribute("customSlug", customSlug);
		model.addAttribute("categorySlug", categorySlug);
		return "home/category";
	}

	@GetMapping("/detail/{productid}")
	public String productDetail(
			@PathVariable(name = "productid") Optional<Integer> productid, Model model
			,@AuthenticationPrincipal User user) {
		model.addAttribute("product", productServic.findById(productid));
		model.addAttribute("evaluateAvg", evaluateServic.findAvgOfEvaluate(productid.get()));
		if(user != null) {
			model.addAttribute("evalustes", evaluateServic.findThreeEvaluateAndUserEvaluate(user.getUsername(),productid.get()));
		}else {
			model.addAttribute("evaluates",evaluateServic.findThreeEvaluate(productid.get()));
		}
		return "home/product";
	}
}
