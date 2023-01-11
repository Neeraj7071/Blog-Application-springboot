package com.neebal.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neebal.model.User;
import com.neebal.repositry.UserDao;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u=userDao.findByEmail(username);
		return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(),new ArrayList<>());
	}

}
