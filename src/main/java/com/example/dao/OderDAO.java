package com.example.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Oder;
import com.example.entity.User;
@Repository
public interface OderDAO extends JpaRepository<Oder, Integer>{
    public Oder	findByOrderIdStr(String id);
    @Query("select sum(o.totalPrice) from Oder as o")
    public Double totalSale();
    
	@Query("select o from Oder as o where o.address like %?1%")
	public Page<Oder> search(String name,Pageable pageable);
}
