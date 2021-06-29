package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CustomType;
@Repository
public interface CustomTypeDAO extends JpaRepository<CustomType, Integer> {
	
}
