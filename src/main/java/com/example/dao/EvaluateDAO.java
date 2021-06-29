package com.example.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.dto.EvaluateAvgDTO;
import com.example.entity.Evaluate;
@Repository
public interface EvaluateDAO extends JpaRepository<Evaluate, Integer> {
	
	@Query("select new com.example.dto.EvaluateAvgDTO(avg(e.star),count(e)) from Evaluate as e where e.product.id = ?1")
	public EvaluateAvgDTO findAvg(Integer productId);

	@Query("select e from Evaluate as e where e.user.username = ?1 and e.product.id = ?2")
	public List<Evaluate> findAllByUsername(String username,Integer productId);
	
	@Query("select e from Evaluate as e where e.user.username != ?1 and e.product.id = ?2")
	public List<Evaluate> findAllExcludeUsername(String username,Integer productId,Pageable page);
	
	@Query("select e from Evaluate as e where e.product.id = ?1")
	public List<Evaluate> findAllEvaluates(Integer productId,Pageable page);
	
	@Query("select avg(e.star) from Evaluate as e")
	public Double avgStar();
}
