package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Card;
@Repository
public interface CardDAO extends JpaRepository<Card, Integer>{
	
	@Query("select c from Card as c where c.user.username = ?1 and c.product.id = ?2")
	public Card findCardByUserAndProduct(String username,Integer productId);
	
	@Query("select c from Card as c where c.user.username = ?1")
	public List<Card> findAllByUsername(String username);
	
}
