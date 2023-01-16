package com.neebal.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neebal.model.Category;
import com.neebal.model.Post;
import com.neebal.model.User;

public interface PostDao extends JpaRepository<Post,Integer> {
	List<Post> findAllByCategory(Category category);
	
	List<Post> findAllByUser(User user);
	
	List<Post> findAllByTitleContaining(String postTitle);
	
}
