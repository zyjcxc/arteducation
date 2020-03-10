package com.edu.admin.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.server.constants.UserConstants;
import com.edu.admin.server.dao.UserMapper;
import com.edu.admin.server.dto.UserDto;
import com.edu.admin.server.model.SysRoleUser;
import com.edu.admin.server.model.User;
import com.edu.admin.server.model.User.Status;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import com.edu.admin.server.service.SysRoleUserService;
import com.edu.admin.server.service.UserService;
import com.edu.admin.server.utils.UserUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SysRoleUserService sysRoleUserService;

	@Override
	@Transactional
	public User saveUser(UserDto userDto) {
		User user = userDto;
		user.setSalt(DigestUtils
				.md5Hex(UUID.randomUUID().toString() + System.currentTimeMillis() + UUID.randomUUID().toString()));
		user.setPassword(passwordEncoder(user.getPassword(), user.getSalt()));
		user.setStatus(Status.VALID);
//		userDao.save(user);
		// mplus 改版
		userMapper.insert(user);
		saveUserRoles(user.getId(), userDto.getRoleIds());

		log.debug("新增用户{}", user.getUsername());
		return user;
	}

	private void saveUserRoles(Long userId, List<Long> roleIds) {
		if (roleIds != null) {
//			userDao.deleteUserRole(userId);
			// mplus 改版 delete from sys_role_user where userId = #{userId}
			sysRoleUserService.remove(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getUserId, userId));
//			sysRoleUserMapper.delete(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getUserId, userId));
			if (!CollectionUtils.isEmpty(roleIds)) {
//				userDao.saveUserRoles(userId, roleIds);
				// mplus 改版
				// insert into sys_role_user(roleId, userId) values ids.....
				sysRoleUserService.saveBatch(
						roleIds.stream().map(roleId -> new SysRoleUser(userId, roleId))
								.collect(Collectors.toList())
				);

			}
		}
	}

	@Override
	public String passwordEncoder(String credentials, String salt) {
		Object object = new SimpleHash("MD5", credentials, salt, UserConstants.HASH_ITERATIONS);
		return object.toString();
	}

	/*@Override
	public User getUser(String username) {
		return userDao.getUser(username);
	}*/

	@Override
	public void changePassword(String username, String oldPassword, String newPassword) {
//		User u = userDao.getUser(username);
		User u = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
		if (u == null) {
			throw new IllegalArgumentException("用户不存在");
		}

		if (!u.getPassword().equals(passwordEncoder(oldPassword, u.getSalt()))) {
			throw new IllegalArgumentException("密码错误");
		}

//		update sys_user t set t.password = #{password} where t.id = #{id}
//		userDao.changePassword(u.getId(), passwordEncoder(newPassword, u.getSalt()));

		userMapper.update(
				new User().setPassword(passwordEncoder(newPassword, u.getSalt())),
				Wrappers.<User>lambdaUpdate()
						.eq(User::getId, u.getId())

		);

		log.debug("修改{}的密码", username);
	}


	@Override
	@Transactional
	public User updateUser(UserDto userDto) {
//		userDao.update(userDto);
		userMapper.updateById(userDto);
		saveUserRoles(userDto.getId(), userDto.getRoleIds());
		updateUserSession(userDto.getId());

		return userDto;
	}

	private void updateUserSession(Long id) {
		User current = UserUtil.getCurrentUser();
		if (current.getId().equals(id)) {
//			User user = userDao.getById(id);
			User user = userMapper.selectById(id);
			UserUtil.setUserSession(user);
		}
	}

	@Override
	public PageTableResponse queryList(PageTableRequest request) {
		Page<User> page = new Page<>(request.getCurrentPage(),request.getLimit());
		Page<User> userPage = userMapper.selectPage(page, makeQueryConditionWrapper(request));
		return new PageTableResponse((int)userPage.getTotal(), (int)userPage.getTotal(), userPage.getRecords());
	}


	private QueryWrapper<User> makeQueryConditionWrapper(PageTableRequest request) {
		OrderByObject orderByObject = request.getOrderByObject();
//		LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
//		lambdaQueryWrapper.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), User::getUsername);
		QueryWrapper<User> query = Wrappers.query();
		Map<String, Object> params = request.getParams();
		query.like(params.containsKey(User.Column.USERNAME.key()), User.Column.USERNAME.key(), params.get(User.Column.USERNAME.key()));
		query.like(params.containsKey(User.Column.NICKNAME.key()), User.Column.NICKNAME.key(), params.get(User.Column.NICKNAME.key()));
		query.eq(params.containsKey(User.Column.STATUS.key()), User.Column.STATUS.key(), params.get(User.Column.STATUS.key()));
		query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn());
		return query;

	}
}
