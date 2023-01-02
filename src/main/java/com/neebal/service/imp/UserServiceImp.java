package com.neebal.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neebal.exception.ResourceNotFoundException;
import com.neebal.model.User;
import com.neebal.repositry.UserDao;
import com.neebal.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User createUser(User u) {
		return userDao.save(u);
	}

	@Override
	public User updateUser(User user, Integer userId) {
		User userGet=userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "Id", userId));
		user.setUserId(userId);
		return userDao.save(user);
	}

	@Override
	public User getUserById(Integer userId) {
		User user=userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "Id", userId));
		return user;
	}

	@Override
	public List<User> getalldata() {
		List<User> users=userDao.findAll();
		return users;
	}

	@Override
	public User deleteUser(Integer userId) {
		User userGet=userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "Id", userId));
		userDao.delete(userGet);
		return userGet;
	}

	


}
