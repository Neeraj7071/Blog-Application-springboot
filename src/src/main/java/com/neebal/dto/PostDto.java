package com.neebal.dto;

import java.util.Date;

import com.neebal.model.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
	private String title;
	private String content;
	private String image;
	public PostDto(Post post) {
		super();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.image = post.getImage();
	}
	
}
