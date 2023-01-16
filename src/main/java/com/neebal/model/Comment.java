package com.neebal.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.neebal.dto.CommentDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Comment {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	private String comment;
	@ManyToOne
	private User user;
	@ManyToOne
	private Post post;
	public Comment(CommentDto commentDto) {
		super();
		this.comment = commentDto.getComment();
	}
	public Comment(CommentDto commentDto, User user, Post post) {
		super();
		this.comment = commentDto.getComment();
		this.user = user;
		this.post = post;
	}
	
	
	
}
