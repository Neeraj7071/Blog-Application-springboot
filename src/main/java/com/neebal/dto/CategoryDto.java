package com.neebal.dto;

import com.neebal.model.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
	
	private String categoryTitle;
	private String categoryDescription;
	public CategoryDto(Category cat) {
		super();
		this.categoryTitle = cat.getCategoryTitle();
		this.categoryDescription = cat.getCategoryDescription();
	}
	
}
