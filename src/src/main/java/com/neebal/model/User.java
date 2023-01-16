package com.neebal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.neebal.dto.UserDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String Name;
	private String email;
	private String password;
	private String about;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonBackReference
	private List<Post> posts=new ArrayList<>();

	public User(UserDto user) {
		super();
		this.Name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.about = user.getAbout();
	}
	
}
