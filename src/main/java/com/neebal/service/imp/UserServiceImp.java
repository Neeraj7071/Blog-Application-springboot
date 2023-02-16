package com.neebal.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neebal.dto.UserDto;
import com.neebal.exception.ResourceNotFoundException;
import com.neebal.model.User;
import com.neebal.repositry.UserDao;
import com.neebal.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User createUser(UserDto u) {
		if(userDao.findByEmail(u.getEmail())!=null)
			throw new UsernameNotFoundException("User already available");
		return userDao.save(new User(u));
	}

	@Override
	public User updateUser(UserDto user,String email) {
//		User userGet=userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "Id", userId));
		User u=new User(user);
		User uDb=userDao.findByEmail(email);
		u.setUserId(uDb.getUserId());
		u.setEmail(email);
		return userDao.save(u);
	}

	@Override
	public User getUser(String email) {
		User user=userDao.findByEmail(email);
		return user;
	}

	@Override
	public List<User> getalldata() {
		List<User> users=userDao.findAll();
		return users;
	}

	@Override
	public User deleteUser(String email) {
		User userGet=userDao.findByEmail(email);
		userDao.delete(userGet);
		return userGet;
	}

	


}
