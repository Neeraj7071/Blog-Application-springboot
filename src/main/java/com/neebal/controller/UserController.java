package com.neebal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neebal.dto.UserDto;
import com.neebal.model.User;
import com.neebal.security.filter.IAuthenticationFacade;
import com.neebal.service.imp.UserServiceImp;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImp userService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;

    @GetMapping("/get")
    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        String a=authentication.getName();
//        System.out.println(a);
        return a;
    }
    
    
	@PostMapping("/signup")
	public ResponseEntity<User> createUser(@RequestBody UserDto user){
		User u=userService.createUser(user);
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody UserDto user){
		User u=userService.updateUser(user,currentUserNameSimple());
		return new ResponseEntity<User>(u,HttpStatus.UPGRADE_REQUIRED);
	}
	
	@GetMapping("/")
	public ResponseEntity<User> getUser(){
		User u=userService.getUser(currentUserNameSimple());
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}
	@GetMapping("/getalluser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> u=userService.getalldata();
		return new ResponseEntity<List<User>>(u,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<User> deleteUser(){
		User u=userService.deleteUser(currentUserNameSimple());
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}
	
}
