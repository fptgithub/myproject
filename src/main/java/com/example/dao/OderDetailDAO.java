package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.OderDetail;
@Repository
public interface OderDetailDAO extends JpaRepository<OderDetail, Integer>{
	@Query("select o from OderDetail as o where o.oder.id = ?1")
	public List<OderDetail> findAllByOderId(Integer id);
	@Query("select sum(od.quantity) from OderDetail as od")
	public Long totalProductSold();
}
