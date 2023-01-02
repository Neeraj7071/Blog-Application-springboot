package com.neebal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neebal.model.User;
import com.neebal.service.imp.UserServiceImp;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImp userService;
	
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User u=userService.createUser(user);
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
	}
	
	@PatchMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("userId") Integer userId){
		User u=userService.updateUser(user, userId);
		return new ResponseEntity<User>(u,HttpStatus.UPGRADE_REQUIRED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") Integer userId){
		User u=userService.getUserById(userId);
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> u=userService.getalldata();
		return new ResponseEntity<List<User>>(u,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId){
		User u=userService.deleteUser(userId);
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}
	
}
