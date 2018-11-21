package com.edu.admin.server.service;

import com.edu.admin.server.dto.UserDto;
import com.edu.admin.server.model.User;

public interface UserService {

	User saveUser(UserDto userDto);
	
	User updateUser(UserDto userDto);

	String passwordEncoder(String credentials, String salt);

	User getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
