package com.edu.admin.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.server.model.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {

//	boolean save(Permission permission);
//
//	void update(Permission permission);
//
//	void delete(Long id);

	/**
	 * mplus 改版
	 * 根据用户id查询权限
	 * @param
	 * @return
	 * @author mengqa
	 */
	List<Permission> listByUserId(Long userId);

	List<Permission> listByUserId(Long userId, boolean orderByCondition);

	List<Permission> listByUserId(Long userId, boolean orderByCondition, boolean isAsc);

	List<Permission> listAll();

	List<Permission> listByRoleId(Long roleId, boolean orderByCondition, boolean isAsc);
}
