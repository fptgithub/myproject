package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Brand;
@Repository
public interface BrandDAO extends JpaRepository<Brand, Integer>{

}
