package com.neebal.service;

import java.util.List;

import com.neebal.dto.CategoryDto;
import com.neebal.model.Category;

public interface CategoryService {
	//create category
	Category createCategory(CategoryDto categoryDto);
	
	//update category
	Category updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//getall category
	List<Category> getAllCategory();
	
	//get category by id
	Category getById(Integer categoryId);
	
	//delete category by id
	Category deleteById(Integer categoryId);
	
}
