package com.example.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.CategoryDAO;
import com.example.dto.PaginationRequestDTO;
import com.example.entity.Product;
import com.example.servic.CategoryServic;
import com.example.servic.ProductServic;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	ProductServic productServic;
	
	@GetMapping()
	public String index(Model model) {
		return "home/index";
	}
	
	@ModelAttribute(name = "latestproduct")
	public List<Product> latestproduct(){
		return productServic.latestProduct();
	}
	
	@ModelAttribute(name = "comingproduct")
	public List<Product> comingproduct(){
		return productServic.comingProduct();
	}
	
	@ModelAttribute(name = "exclusiveProduct")
	public List<Product> exclusiveProduct(){
		return productServic.exclusiveProduct();
	}
}
