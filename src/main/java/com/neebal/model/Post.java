package com.neebal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.neebal.dto.PostDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	private String title;
	private String content;
	private String image;
	private Date addDate;
	@ManyToOne
//	@JoinColumn(name="categoryId")
	private Category category;
	@ManyToOne
//	@JoinColumn(name="userId")
	private User user;
	public Post(PostDto post) {
		super();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.image = post.getImage();
		this.addDate=new Date();
		}
	
}