package com.neebal.service;

import java.util.List;

import com.neebal.dto.PostDto;
import com.neebal.dto.ResponseDto;
import com.neebal.model.Post;

public interface PostService {
	// create Post
    Post createPost(PostDto postDto,Integer categoryId , Integer userId);
    
    // update Post
    Post updatePost(PostDto postDto , Integer postId);
    
    // delete post
    Post deletePost(Integer postId);
    
    // find all post
    ResponseDto getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
    
    // find single post
    Post getPostById(Integer postId);
    
    // find by category
    List<Post> getPostByCategory(Integer categoryId);
    
    // find by user
    List<Post> getPostByUser(Integer userId);
    
    // search by post
    List<Post> searchPostS(String keyWord);
}
