package com.edu.admin.server.config;

import com.edu.admin.server.model.Permission;
import com.edu.admin.server.model.Role;
import com.edu.admin.server.model.User;
import com.edu.admin.server.model.User.Status;
import com.edu.admin.server.service.PermissionService;
import com.edu.admin.server.service.RoleService;
import com.edu.admin.server.service.UserService;
import com.edu.admin.server.utils.UserUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;




public class MyShiroRealm extends AuthorizingRealm {
	
	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	/**
	 * mplus 新版
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		String username = usernamePasswordToken.getUsername();
//		UserService userService = SpringUtil.getBean(UserService.class);
//		User user = userService.getUser(username);

		// mplus 改动
		User user = userService.lambdaQuery().eq(User::getUsername, username).one();
		if (user == null) {
			throw new UnknownAccountException("用户名不存在");
		}

		if (!user.getPassword()
				.equals(userService.passwordEncoder(new String(usernamePasswordToken.getPassword()), user.getSalt()))) {
			throw new IncorrectCredentialsException("密码错误");
		}

		if (user.getStatus() != Status.VALID) {
			throw new IncorrectCredentialsException("无效状态，请联系管理员");
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), getName());

		UserUtil.setUserSession(user);

		return authenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.debug("权限配置");

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = UserUtil.getCurrentUser();

//		List<Role> roles = SpringUtil.getBean(RoleDao.class).listByUserId(user.getId());
		// select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = #{userId}
		List<Role> roles = roleService.listByUserId(user.getId());
		Set<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toSet());
		authorizationInfo.setRoles(roleNames);
//		List<Permission> permissionList = SpringUtil.getBean(PermissionMapper.class).listByUserId(Wrappers.<Permission>lambdaQuery().eq(Permission::getUserid, user.getId()));
		List<Permission> permissionList = permissionService.listByUserId(user.getId(), true, true);
		UserUtil.setPermissionSession(permissionList);
		Set<String> permissions = permissionList.stream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(Permission::getPermission).collect(Collectors.toSet());
		authorizationInfo.setStringPermissions(permissions);

		return authorizationInfo;
	}

	/**
	 * 重写缓存key，否则集群下session共享时，会重复执行doGetAuthorizationInfo权限配置
	 */
	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
		Object object = principalCollection.getPrimaryPrincipal();

		if (object instanceof User) {
			User user = (User) object;

			return "authorization:cache:key:users:" + user.getId();
		}

		return super.getAuthorizationCacheKey(principals);
	}

}
