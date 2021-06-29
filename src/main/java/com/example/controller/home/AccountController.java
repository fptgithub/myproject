package com.example.controller.home;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.RegisterDTO;
import com.example.dto.RegisterRequestDTO;
import com.example.dto.code;
import com.example.servic.UserServic;
import com.example.utils.EmailUtils;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	UserServic userServic;
	
	@Autowired
	EmailUtils emailUtils;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/login")
	public String loginIndex(@RequestParam(value = "error",required = false) String error,Model model) {
		if(error!=null) {
			model.addAttribute("message","Invalid username or password!");
		}
		return "home/login";
	}
	
	@GetMapping("/edit")
	public String editAccount(Model model,@AuthenticationPrincipal User user) {
		RegisterRequestDTO dto = new RegisterRequestDTO();
		dto.setSex(userServic.getUserByUsername(user.getUsername()).get().getSex());
		model.addAttribute("registerObject", dto);
		return "home/editaccount";
	}
	
	@GetMapping("/forgotpass")
	public String forgotview() {
		return "home/forgotpass";
	}
	
	@PostMapping("/forgotpass")
	public String forgot(@RequestParam(value = "email",required = false) String email,Model model) {
		String coderandom = RandomStringUtils.randomAlphanumeric(10);
		emailUtils.putMimeMessage(email, "your code", coderandom+" : " +"it lasts for 5 minutes");
		code c = new code();
		c.setEmail(email);
		c.setCode(coderandom);
		emailUtils.addcode(c);
		
		return "home/checkforgot";
	}
	
	@PostMapping("/checkforgot")
	public String checkforgot(@RequestParam("code") String co,@RequestParam("pass") String pass,Model model) {
		code c = emailUtils.findcode(co);
		if(c==null) {
			model.addAttribute("message", "code not exist!");
			return "home/checkforgot";
		}
		if(!c.isTimeout()) {
			model.addAttribute("message", "it timeout!");
			return "home/checkforgot";
		}
		com.example.entity.User u = userServic.findbyEmail(c.getEmail());
		u.setPassword(encoder.encode(pass));
		userServic.updateUser(u);
		model.addAttribute("message", "change pass success");
		return "home/checkforgot";
	}
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("registerObject") RegisterRequestDTO dto,BindingResult result,Model model,@AuthenticationPrincipal User user) {
		if(result.hasErrors()) {
			return "home/editaccount";
		}else {
			if(!userServic.equalPassTooldpass(dto.getOldPassword(), userServic.getUserByUsername(user.getUsername()).get().getPassword())
					||(!userServic.checkEmail(dto.getEmail())&&!userServic.getUserByUsername(user.getUsername()).get().getEmail().equals(dto.getEmail()))
					||!userServic.equalPassToPassAgain(dto.getPassword(),dto.getPasswordAgain())) {
				if(!userServic.checkUsername(dto.getUsername())) {
					model.addAttribute("message","username is exists");
				}else if(!userServic.checkEmail(dto.getEmail())) {
					model.addAttribute("message","email is exists");
				}else if(!userServic.equalPassToPassAgain(dto.getPassword(),dto.getPasswordAgain())){
					model.addAttribute("message","two password not same");
				}else if(!userServic.equalPassTooldpass(dto.getOldPassword(), userServic.getUserByUsername(user.getUsername()).get().getPassword())){
					model.addAttribute("message","old pass not same");
				}
				return "home/editaccount";
			}else {
				dto.setUsername(user.getUsername());
				userServic.update(dto);
				return "redirect:/home";
			}
		}
	}
	
	@GetMapping("/register")
	public String registerIndex(Model model) {
		model.addAttribute("registerObject", new RegisterRequestDTO());
		return "home/register";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registerObject") RegisterDTO dto,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "home/register";
		}else {
			if(!userServic.checkUsername(dto.getUsername())
					||!userServic.checkEmail(dto.getEmail())
					||!userServic.equalPassToPassAgain(dto.getPassword(),dto.getPasswordAgain())) {
				if(!userServic.checkUsername(dto.getUsername())) {
					model.addAttribute("message","username is exists");
				}else if(!userServic.checkEmail(dto.getEmail())) {
					model.addAttribute("message","email is exists");
				}else if(!userServic.equalPassToPassAgain(dto.getPassword(),dto.getPasswordAgain())){
					model.addAttribute("message","two password not same");
				}
				return "home/register";
			}else {
				userServic.register(dto);
				return "redirect:/account/login";
			}
		}
	}
	
	
}
