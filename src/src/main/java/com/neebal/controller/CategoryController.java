package com.neebal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neebal.dto.CategoryDto;
import com.neebal.model.Category;
import com.neebal.service.imp.CategoryServiceImp;

@RestController
@RequestMapping("/Category")
public class CategoryController {
	@Autowired
	private CategoryServiceImp categoryService;

	@PostMapping("/")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
		Category u = categoryService.createCategory(categoryDto);
		return new ResponseEntity<Category>(u, HttpStatus.CREATED);
	}

	@PatchMapping("/update/{categoryId}")
	public ResponseEntity<Category> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId) {
		Category u = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<Category>(u, HttpStatus.UPGRADE_REQUIRED);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Integer categoryId) {
		Category u = categoryService.getById(categoryId);
		return new ResponseEntity<Category>(u, HttpStatus.ACCEPTED);
	}

	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> u = categoryService.getAllCategory();
		return new ResponseEntity<List<Category>>(u, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		Category u = categoryService.deleteById(categoryId);
		return new ResponseEntity<Category>(u, HttpStatus.ACCEPTED);
	}

}
