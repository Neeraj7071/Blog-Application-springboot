package com.neebal.dto;

import java.util.List;

import com.neebal.model.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
	List<Post> list;
	Integer pageNumber;
	Integer pageSize;
	Integer totalPages;
	Integer totalElement;
	String sortBy;
	String SortDir;
	
}
