package com.neebal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neebal.dto.PostDto;
import com.neebal.dto.ResponseDto;
import com.neebal.model.Post;
import com.neebal.security.filter.IAuthenticationFacade;
import com.neebal.service.imp.FileServiceImp;
import com.neebal.service.imp.PostServiceImp;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostServiceImp postImp;
	
	@Autowired
	private FileServiceImp fileImp;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;

    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        String a=authentication.getName();
//        System.out.println(a);
        return a;
    }
	
	@Value("${project.image}")
	private String path;
	@PostMapping("/category/{categoryId}")
	public ResponseEntity<Post> createPost(@RequestBody PostDto postDto,@PathVariable("categoryId") Integer categoryId) {
		Post postGet = postImp.createPost(postDto, categoryId, currentUserNameSimple());
		return new ResponseEntity<Post>(postGet, HttpStatus.CREATED);
	}

	@PutMapping("/update/{postId}")
	public ResponseEntity<Post> updatePostById(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId) {
		Post postGet = postImp.updatePost(postDto, postId,currentUserNameSimple());
		return new ResponseEntity<Post>(postGet, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<Post> deletePostById(@PathVariable("postId") Integer postId) {
		Post postGet = postImp.deletePost(postId,currentUserNameSimple());
		return new ResponseEntity<Post>(postGet, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/")
	public ResponseEntity<ResponseDto> findAll(@RequestParam(value="pageNumber",defaultValue="0",required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="5",required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue="postId",required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue="Asc",required=false)String dir) {
		ResponseDto resDto= postImp.getAllPost(pageNumber,pageSize,sortBy,dir);
		return new ResponseEntity<ResponseDto>(resDto, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/byID/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable("postId") Integer postId) {
		Post post = postImp.getPostById(postId);
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/byCategoryId/{categoryId}")
	public ResponseEntity<List<Post>> getByCategoryId(@PathVariable("categoryId") Integer categoryId) {
		List<Post> posts = postImp.getPostByCategory(categoryId);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/byuser")
	public ResponseEntity<List<Post>> getByUserId() {
		List<Post> posts = postImp.getPostByUser(currentUserNameSimple());
		return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/byKeyWord/{keyWord}")
	public ResponseEntity<List<Post>> getByKeyWord(@PathVariable("keyWord") String keyWord) {
		List<Post> posts = postImp.searchPostS(keyWord);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/image/upload/{postId}")
	public ResponseEntity<Post> uploadimage(@RequestParam("image") MultipartFile image,@PathVariable("postId")Integer postId) throws IOException{
		String fileName=fileImp.uploadImage(path, image);
		Post post=postImp.getPostById(postId);
		post.setImage(fileName);
		Post postUpdate=postImp.updatePost(new PostDto(post), postId,currentUserNameSimple());
		return new ResponseEntity<Post>(postUpdate, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping(value="/profile/files/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException{
		InputStream resource=fileImp.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
}

