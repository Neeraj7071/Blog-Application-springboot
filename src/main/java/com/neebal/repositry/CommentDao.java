package com.neebal.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neebal.model.Comment;
import com.neebal.model.Post;
import com.neebal.model.User;

public interface CommentDao extends JpaRepository<Comment,Integer> {
	List<Comment> findAllByPost(Post post);
	List<Comment> findAllByUser(User user);
}
