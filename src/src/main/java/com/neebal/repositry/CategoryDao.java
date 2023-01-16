package com.neebal.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neebal.model.Category;

public interface CategoryDao extends JpaRepository<Category,Integer>{

}
