package com.neebal.service;

import java.util.List;

import com.neebal.model.User;

public interface UserService {
	
	//savedata
	User createUser(User u);
	//update data
	User updateUser(User user,Integer userId);
	//get user by id
	User getUserById(Integer userId);
	//get all user
	List<User> getalldata();
	//delete user
	User deleteUser(Integer userId);
	
}
