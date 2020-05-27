package com.invertorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invertorySystem.bo.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	public Category findById(long categoryId); 
	
	public Category findByCategoryCode(String categoryCode);
	
	public Category findByCategoryName(String categoryName);
}
