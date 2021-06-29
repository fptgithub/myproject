package com.example.controller.admin;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.OderDAO;
import com.example.dto.PaginationRequestDTO;
import com.example.entity.Oder;
import com.example.entity.User;
import com.example.servic.OrderServic;
import com.example.utils.EOrderState;

@Controller
@RequestMapping("/admin/order")
public class OderController {
	
	@Autowired
	OrderServic orderServic;
	@Autowired
	OderDAO dao;

	@GetMapping("/{orderid}")
	public String orderDetail(@PathVariable("orderid") Integer orderid,Model model) {
		model.addAttribute("orderdetails", orderServic.findAllByOrderId(orderid));
		return "admin/table/orderdetail";
	}
	
	@GetMapping("/table")
	public String table(@ModelAttribute(name = "pageRequest")PaginationRequestDTO pRequest
			,Model model) {
		Page<Oder> o = null;
		if(pRequest.getSearch()!=null) {
			o = orderServic.search(pRequest.getSearch(),pRequest,10);
		}else {
		 o = orderServic.paginationAndSort(pRequest,10);
		}
		model.addAttribute("pageableOrder",o);
		model.addAttribute("pagination", orderServic.caculatorPage(o));
		return "admin/table/order";
	}
	
	@ResponseBody
	@PostMapping("/update")
	public String update(@RequestParam("id") Integer id,@RequestParam("state") String state) {
		Oder o = dao.findById(id).get();
		
		o.setState(EOrderState.valueOf(state));
		dao.save(o);
		return "ok";
	}
	
	@ModelAttribute("order")
	public List<String> orderstate(){
		return Arrays.asList(EOrderState.RECEIVE.name(),EOrderState.RECEIVED.name(),EOrderState.TRANSPORT.name());
	}
	
}
