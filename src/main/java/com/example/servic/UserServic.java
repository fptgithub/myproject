package com.example.servic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.UserDAO;
import com.example.dto.DashBoardDTO;
import com.example.dto.PaginationRequestDTO;
import com.example.dto.RegisterDTO;
import com.example.dto.RegisterRequestDTO;
import com.example.entity.Product;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.utils.ERole;

@Service
public class UserServic {
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	PasswordEncoder encoder;
	
	public Optional<User> getUserByUsername(String username) {
		return userDAO.findByUsername(username);
	}
	
	public Page<User> search(String name,PaginationRequestDTO pRequest,int defaultLimit){
		Pageable pageable = PageRequest.of(pRequest.getPage().orElse(0), pRequest.getLimit().orElse(defaultLimit),
				Sort.by(pRequest.getSort(), pRequest.getSortBy().orElse("username")));
		return userDAO.search(name,pageable);
	}
	
	public User findbyEmail(String email) {
		return userDAO.findByEmail(email).get();
	}
	
	public void register(RegisterDTO dto) {
		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.builder().name(ERole.USER).build());
		User user = User.builder()
				.password(encoder.encode(dto.getPassword()))
				.username(dto.getUsername())
				.email(dto.getEmail())
				.sex(dto.getSex())
				.roles(roles)
				.phone("")
				.build();
		userDAO.save(user);
	}
	
	public Page<User> paginationAndSort(PaginationRequestDTO pRequest,int defaultLimit) {
		Pageable pageable = PageRequest.of(pRequest.getPage().orElse(0), pRequest.getLimit().orElse(defaultLimit),
				Sort.by(pRequest.getSort(), pRequest.getSortBy().orElse("username")));
		return userDAO.findAll(pageable);
	}
	
	public List<Integer> caculatorPage(Page<User> p) {
		List<Integer> list = new ArrayList<Integer>();
		// neu page la 1 hoac 2 thi bat dau tu 1 cho den 5
		// neu page lon hon 2 thi lay page hien tai lam trung tam, lay 2 page trai,phai
		// hai ben
		for (int i = p.getNumber() - 2 >= 1 ? p.getNumber() - 2 : 1,
				j = 1; i <= (p.getNumber() - 2 >= 1 ? p.getNumber() + 2 : 5); i++) {
			// neu page hien tai vuot qua totalpage thi chuyen nguoc page ve vi tri dau tien
			if (i > p.getTotalPages()-1) {
				if (p.getNumber() - j <= 0)
					break;
				if ((p.getNumber() - j - 2) >= 1) {
					list.add(0, p.getNumber() - j - 2);
				}
				j++;
			} else if (i >= 1) {
				list.add(i);
			}
		}
		return list;
	}
	
	public boolean equalPassTooldpass(String password,String passold) {
		return encoder.matches(password,passold);
	}
	
	public boolean checkUsername(String username) {
		return userDAO.findByUsername(username).isEmpty();
	}
	
	public boolean checkEmail(String username) {
		return userDAO.findByEmail(username).isEmpty();
	}
	
	public void updateUser(User u) {
		userDAO.save(u);
	}
	
	public void update(RegisterRequestDTO dto) {
		User u = userDAO.findByUsername(dto.getUsername()).get();
		u.setEmail(dto.getEmail());
		u.setPassword(encoder.encode(dto.getPassword()));
		u.setSex(dto.getSex());
		userDAO.save(u);
	}
	
	public boolean equalPassToPassAgain(String password,String passwordAgain) {
		return password.equals(passwordAgain);
	}
	
}
