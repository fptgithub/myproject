package com.example.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	@Query("select p from Product as p where p.customtype.slug = ?1")
	public Page<Product> findAllProductByCustomtypeAndPageable(String customtypeSlug,Pageable pageable);
	
	@Query("select p from Product as p where p.customtype.slug = ?1 and p.category.slug = ?2")
	public Page<Product> findAllProductByCategoryAndPageable(String customtypeSlug,String categorySlug,Pageable pageable);
	
	@Query("select p from Product as p where p.customtype.slug = ?1 and p.category.slug = ?2 and p.slug = ?3")
	public Product findProductBySlug(String customtypeSlug,String categorySlug,String productSlug);
	
	@Query("select distinct p from Product as p order by (p.price/p.salePrice) desc")
	public List<Product> dealOfWeek(Pageable page);
	
	@Query("select p from Product as p order by RAND()")
	public List<Product> findByLatest(Pageable page);
	
	@Query("select p from Product as p order by RAND()")
	public List<Product> findByComing(Pageable page);
	
	@Query("select p from Product as p order by RAND()")
	public List<Product> exclusiveProduct(Pageable page);
	
	@Query("select p from Product as p where p.name like %?1%")
	public Page<Product> search(String name,Pageable pageable);
		
}
