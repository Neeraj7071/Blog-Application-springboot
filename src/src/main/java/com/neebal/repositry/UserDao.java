package com.neebal.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.neebal.model.User;

public interface UserDao extends JpaRepository<User ,Integer>{

	User findByEmail(String email);
	

}
