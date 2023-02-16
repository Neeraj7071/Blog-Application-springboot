package com.neebal.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neebal.dto.CommentDto;
import com.neebal.exception.ResourceNotFoundException;
import com.neebal.model.Comment;
import com.neebal.model.Post;
import com.neebal.model.User;
import com.neebal.repositry.CommentDao;
import com.neebal.repositry.PostDao;
import com.neebal.service.CommentService;


@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private PostServiceImp postImp;
	
	@Autowired
	private UserServiceImp userImp;
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public Comment createComment(Integer postId, CommentDto commentDto, String email) {
		Post post=postImp.getPostById(postId);
		User user=userImp.getUser(email);
		Comment comment=new Comment(commentDto,user, post);
		return commentDao.save(comment);
		
	}

	@Override
	public Comment updateComment(Integer commentId, CommentDto commentDto, String email) {
		Comment comment=commentDao.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		User user=userImp.getUser(email);
		if(!comment.getUser().equals(user))
			throw new ResourceNotFoundException("Comment", "Id", commentId);
		comment.setComment(commentDto.getComment());
		return commentDao.save(comment);
	}

	@Override
	public Comment deleteComment(Integer commentId, String email) {
		Comment comment=commentDao.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		User user=userImp.getUser(email);
		if(!comment.getUser().equals(user))
			throw new ResourceNotFoundException("Comment", "Id", commentId);
		commentDao.deleteById(commentId);
		return comment;
	}

	@Override
	public List<CommentDto> getAllCommentByPostId(Integer postId) {
		Post post=postImp.getPostById(postId);
		List<Comment> comments=commentDao.findAllByPost(post);
		return (List<CommentDto>) comments.stream().map(comment-> new CommentDto(comment));
	}

	@Override
	public List<CommentDto> getAllCommentByuser(String email) {
		User user=userImp.getUser(email);
		List<Comment> comments=commentDao.findAllByUser(user);
		return (List<CommentDto>) comments.stream().map(comment-> new CommentDto(comment));
	}

}
