package com.neebal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private String Name;
	private String email;
	private String password;
	private String about;
}
