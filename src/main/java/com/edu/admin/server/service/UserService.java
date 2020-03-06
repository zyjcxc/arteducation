package com.edu.admin.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.server.dto.UserDto;
import com.edu.admin.server.model.User;

/**
 * mplus2020.3.6改版 （extends IService<User>）
 * @author mengqa
 */
public interface UserService extends IService<User> {

	User saveUser(UserDto userDto);
	
	User updateUser(UserDto userDto);

	String passwordEncoder(String credentials, String salt);

//	User getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);




}
