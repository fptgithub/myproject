package com.example.controller.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.PaginationRequestDTO;
import com.example.entity.Category;
import com.example.entity.CustomType;
import com.example.entity.Product;
import com.example.servic.CategoryServic;
import com.example.servic.CustomTypeServic;
import com.example.servic.ProductServic;

@Controller
@RequestMapping("/admin/product")
public class ProductControllerAdmin {
	
	@Autowired
	ProductServic productServic;
	@Autowired
	CategoryServic categoryServic;
	@Autowired
	CustomTypeServic customtypeServic;
	
	@GetMapping("/table")
	public String table(@ModelAttribute(name = "pageRequest")PaginationRequestDTO pRequest
			,Model model) {
		Page<Product> p = null;
		if(pRequest.getSearch()!=null) {
			p = productServic.search(pRequest.getSearch(),pRequest,10);
		}else {
		 p = productServic.paginationAndSort(pRequest,10);
		}
		model.addAttribute("pageableProduct",p);
		model.addAttribute("pagination", productServic.caculatorPage(p));
		return "admin/table/product";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("product", Product.builder().createDate(LocalDate.now()).build());
		return "admin/form/product";
	}
	
	@GetMapping("/edit/{product_id}")
	public String edit(@PathVariable(name = "product_id") Optional<Integer> id
			,Model model) {
		model.addAttribute("product", productServic.findById(id));
		return "admin/form/product";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute(name = "product") Product product,BindingResult rs) {
		System.out.println(product);
		if(rs.hasErrors()) {
			return "admin/form/product";
		}
		productServic.saveProduct(product);
		return "redirect:/admin/product/table";
	}
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute(name = "product") Product product,BindingResult rs) {
		if(rs.hasErrors()) {
			return "/admin/product/edit/"+product.getId();
		}
		productServic.updateProduct(product);
		return "redirect:/admin/product/edit/"+product.getId();
	}
	
	@GetMapping("/delete/{product_id}")
	public String delete(@PathVariable(name = "product_id") Optional<Integer> id) {
		productServic.deleteProductById(id);
		return "redirect:/admin/product/table";
	}
	
	@ModelAttribute(name = "categorys")
	public Map<Category, String> categorys(){
		return categoryServic.findAllCategory();
	}
	
	@ModelAttribute(name = "customtypes")
	public Map<CustomType,String> customtypes(){
		return customtypeServic.findAllCustomType();
	}
}
