package com.neebal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neebal.dto.CommentDto;
import com.neebal.model.Comment;
import com.neebal.repositry.CommentDao;
import com.neebal.security.filter.IAuthenticationFacade;
import com.neebal.service.imp.CommentServiceImp;

@RestController
@RequestMapping("/commit")
public class CommentController {
	
	@Autowired
	private CommentServiceImp commentImp;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;

    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        String a=authentication.getName();
//        System.out.println(a);
        return a;
    }
	
    @PostMapping("/createcomment/{postId}")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto,@PathVariable("postId") Integer postId) {
    	Comment comment=commentImp.createComment(postId, commentDto,currentUserNameSimple());
    	return new ResponseEntity<Comment>(comment,HttpStatus.CREATED);
    }
    
    @PatchMapping("/updateComment/{postId}")
    public ResponseEntity<Comment> updateComment(@RequestBody CommentDto commentDto,@PathVariable("postId") Integer postId) {
    	Comment comment=commentImp.updateComment(postId, commentDto,currentUserNameSimple());
    	return new ResponseEntity<Comment>(comment,HttpStatus.UPGRADE_REQUIRED);
    }
	
    @DeleteMapping("/deleteComment/{postId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("postId") Integer postId) {
    	Comment comment=commentImp.deleteComment(postId,currentUserNameSimple());
    	return new ResponseEntity<Comment>(comment,HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/getByPostId/{postId}")
    public ResponseEntity<List<CommentDto>> getByPostId(@PathVariable("postId") Integer postId){
    	List<CommentDto> list=commentImp.getAllCommentByPostId(postId);
    	return new ResponseEntity<List<CommentDto>>(list,HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/getByUser")
    public ResponseEntity<List<CommentDto>> getByUser(){
    	List<CommentDto> list=commentImp.getAllCommentByuser(currentUserNameSimple());
    	return new ResponseEntity<List<CommentDto>>(list,HttpStatus.ACCEPTED);
    }
    
}
