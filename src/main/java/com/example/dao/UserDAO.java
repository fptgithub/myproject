package com.example.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.dto.DashBoardDTO;
import com.example.entity.Product;
import com.example.entity.User;
@Repository
public interface UserDAO extends JpaRepository<User, String>{
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByEmail(String email);
	
	@Query("select u from User as u where u.username like %?1%")
	public Page<User> search(String name,Pageable pageable);
	
}
