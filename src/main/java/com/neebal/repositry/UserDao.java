package com.neebal.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neebal.model.User;

public interface UserDao extends JpaRepository<User ,Integer>{

}
