package com.neebal.dto;

import com.neebal.model.Comment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
	private String comment;
	private String user;
	public CommentDto(Comment comment) {
		super();
		this.comment = comment.getComment();
		this.user = comment.getUser().getEmail();
	}
	
	
	
}
