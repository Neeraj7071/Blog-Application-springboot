package com.neebal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.neebal.dto.CategoryDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	private String categoryTitle;
	private String categoryDescription;
	
	@OneToMany(mappedBy="category",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonBackReference
	private List<Post> posts=new ArrayList<>();

	public Category(CategoryDto cat) {
		super();
		this.categoryTitle = cat.getCategoryTitle();
		this.categoryDescription = cat.getCategoryDescription();
	}
	
}
