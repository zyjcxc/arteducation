package com.edu.admin.server.controller;

import com.edu.admin.server.annotation.LogAnnotation;
import com.edu.admin.server.dto.UserDto;
import com.edu.admin.server.model.User;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import com.edu.admin.server.service.UserService;
import com.edu.admin.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户相关接口
 * 
 * @author 小威老师
 *
 */
@Api(tags = "用户")

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private UserService userService;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存用户")
	@RequiresPermissions("sys:user:add")
	public User saveUser(@RequestBody UserDto userDto) {
//		User u = userService.getUser(userDto.getUsername());
		// mplus 改版
		User u = userService.lambdaQuery().select(User::getId).
				eq(User::getUsername, userDto.getUsername()).one();
		if (u != null) {
			throw new IllegalArgumentException(userDto.getUsername() + "已存在");
		}

		return userService.saveUser(userDto);
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改用户")
	@RequiresPermissions("sys:user:add")
	public User updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}

	@LogAnnotation
	@PutMapping(params = "headImgUrl")
	@ApiOperation(value = "修改头像")
	public void updateHeadImgUrl(String headImgUrl) {
		User user = UserUtil.getCurrentUser();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		userDto.setHeadImgUrl(headImgUrl);

		userService.updateUser(userDto);
		log.debug("{}修改了头像", user.getUsername());
	}

	@LogAnnotation
	@PutMapping("/{username}")
	@ApiOperation(value = "修改密码")
	@RequiresPermissions("sys:user:password")
	public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
		userService.changePassword(username, oldPassword, newPassword);
	}

	@GetMapping
	@ApiOperation(value = "用户列表")
	@RequiresPermissions("sys:user:query")
	public PageTableResponse listUsers(PageTableRequest request) {

		/*return new PageTableHandler(new ListHandler() {
			@Override
			public List<User> list(PageTableRequest request) {
				IPage<User> page = userService.queryList(new Page<>(1,2));

				List<User> list = userDao.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handleMplus(request);*/

		return userService.queryList(request);
	}

	@ApiOperation(value = "当前登录用户")
	@GetMapping("/current")
	public User currentUser() {
		return UserUtil.getCurrentUser();
	}

	@ApiOperation(value = "根据用户id获取用户")
	@GetMapping("/{id}")
	@RequiresPermissions("sys:user:query")
	public User user(@PathVariable Long id) {
		return userService.getById(id);
	}

}
