package com.neebal.service;

import java.util.List;

import com.neebal.dto.CommentDto;
import com.neebal.model.Comment;

public interface CommentService {
		
	Comment createComment(Integer postId,CommentDto comment,String email);
	
	Comment updateComment(Integer commentId,CommentDto comment,String email);
	
	Comment deleteComment(Integer commentId,String User);
	
	List<CommentDto> getAllCommentByPostId(Integer postId);

	List<CommentDto> getAllCommentByuser(String email);
	
}
