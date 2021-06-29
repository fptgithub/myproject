package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Category;
@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer>{
	@Query("select custom.name,custom.slug,c.name,c.slug from Category as "
			+ "c join c.products as p join p.customtype as custom "
			+ "group by custom.name,custom.slug,c.name,c.slug "
			+ "order by custom.name")
	public List<Object[]> findAllCategoryAndCustomType();
}
