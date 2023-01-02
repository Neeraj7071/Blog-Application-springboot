package com.neebal.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neebal.dto.CategoryDto;
import com.neebal.exception.ResourceNotFoundException;
import com.neebal.model.Category;
import com.neebal.repositry.CategoryDao;
import com.neebal.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Category createCategory(CategoryDto categoryDto) {
		Category category=new Category(categoryDto);
		return categoryDao.save(category);
	}

	@Override
	public Category updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category=new Category(categoryDto);
		Category categoryGet=categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryId));
		category.setCategoryId(categoryId);
		return categoryDao.save(category);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDao.findAll();
	}

	@Override
	public Category getById(Integer categoryId) {
		Category category=categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryId));
		return category;
	}

	@Override
	public Category deleteById(Integer categoryId) {
		Category category=categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryId));
		categoryDao.delete(category);
		return category;
	}

}
