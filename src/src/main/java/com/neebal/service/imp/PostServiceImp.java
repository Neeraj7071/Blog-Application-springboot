package com.neebal.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.neebal.dto.PostDto;
import com.neebal.dto.ResponseDto;
import com.neebal.exception.ResourceNotFoundException;
import com.neebal.model.Category;
import com.neebal.model.Post;
import com.neebal.model.User;
import com.neebal.repositry.PostDao;
import com.neebal.service.PostService;


@Service
public class PostServiceImp implements PostService{

	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserServiceImp userImp;
	
	@Autowired
	private CategoryServiceImp categoryImp;
	
	

	@Override
	public Post createPost(PostDto postDto, Integer categoryId, String email) {
		Post post=new Post(postDto);
		User user=userImp.getUser(email);
		Category category=categoryImp.getById(categoryId);
		post.setUser(user);
		post.setCategory(category);
		return postDao.save(post);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId,String email) {
		Post post=new Post(postDto);
		Post postget=postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		if(!post.getUser().equals(userImp.getUser(email)))
			throw new ResourceNotFoundException("Post", "Id", postId);
		post.setPostId(postId);
		return postDao.save(post);
	}

	@Override
	public Post deletePost(Integer postId,String email) {
		Post postget=postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		
		if(!postget.getUser().equals(userImp.getUser(email)))
			throw new ResourceNotFoundException("Post", "Id", postId);postDao.delete(postget);
		return postget;
	}



	@Override
	public Post getPostById(Integer postId) {
		Post postget=postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		
		return postget;
	}

	@Override
	public List<Post> getPostByCategory(Integer categoryId) {
		Category category=categoryImp.getById(categoryId);
		List<Post> posts=postDao.findAllByCategory(category);
		return posts;
	}

	@Override
	public List<Post> getPostByUser(String email){
		User user=userImp.getUser(email);
		List<Post> posts=postDao.findAllByUser(user);
		return posts;
	}

	@Override
	public List<Post> searchPostS(String keyWord) {
		List<Post> posts=postDao.findAllByTitleContaining(keyWord);
		return posts;
	}

	@Override
	public ResponseDto getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> posts=postDao.findAll(p);
		ResponseDto resDto=new ResponseDto(posts.getContent(),pageNumber,pageSize,posts.getTotalPages(),posts.getNumberOfElements(),sortBy,sortDir);
		return resDto;
	}
	
}
