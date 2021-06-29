package com.example.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.UserDAO;
import com.example.servic.UserServic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
	
	@NotBlank(message = "username not empty!")
	@Size(min = 3, max = 30, message = "length of username between 3 to 30!")
	private String username;
	@NotBlank(message = "password not empty!")
	@Size(min = 6, max = 30, message = "length of password between 6 to 30!")
	private String password;
	@NotBlank(message = "old password not empty!")
	@Size(min = 6, max = 30, message = "length of password between 6 to 30!")
	private String oldPassword;
	@NotBlank(message = "password not empty!")
	@Size(min = 6, max = 30, message = "length of password between 6 to 30!")
	private String passwordAgain;
	@NotBlank(message = "email not empty!")
	@Email(message = "email not correctly!")
	private String email;
	@NotBlank(message = "sex not empty!")
	private String sex;

	public Map<String, String> renderSex() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("male", "male");
		map.put("female", "female");
		return map;
	}

	
}
