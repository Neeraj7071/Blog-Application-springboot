package com.neebal.service;

import java.util.List;

import com.neebal.dto.UserDto;
import com.neebal.model.User;

public interface UserService {
	
	//savedata
	User createUser(UserDto u);
	//update data
	User updateUser(UserDto user,String email);
	//get user by id
	User getUser(String email);
	//get all user
	List<User> getalldata();
	//delete user
	User deleteUser(String email);
	
}
