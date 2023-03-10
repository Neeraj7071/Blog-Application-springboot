package com.neebal.service;

import java.util.List;

import com.neebal.dto.PostDto;
import com.neebal.dto.ResponseDto;
import com.neebal.model.Post;

public interface PostService {
	// create Post
    Post createPost(PostDto postDto,Integer categoryId ,String email);
    
    // update Post
    Post updatePost(PostDto postDto , Integer postId,String email);
    
    // delete post
    Post deletePost(Integer postId,String email);
    
    // find all post
    ResponseDto getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
    
    // find single post
    Post getPostById(Integer postId);
    
    // find by category
    List<Post> getPostByCategory(Integer categoryId);
    
    // find by user
    List<Post> getPostByUser(String email);
    
    // search by post
    List<Post> searchPostS(String keyWord);
}
