package com.edu.admin.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.server.dao.PermissionDao;
import com.edu.admin.server.dao.PermissionMapper;
import com.edu.admin.server.model.Permission;
import com.edu.admin.server.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	private static final Logger log = LoggerFactory.getLogger("PermissionServiceImpl");

	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public boolean save(Permission permission) {
		permissionDao.save(permission);

		log.debug("新增菜单{}", permission.getName());
        return false;
    }

	@Override
	public void update(Permission permission) {
		permissionDao.update(permission);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		permissionDao.deleteRolePermission(id);
		permissionDao.delete(id);
		permissionDao.deleteByParentId(id);

		log.debug("删除菜单id:{}", id);
	}

	@Override
	public List<Permission> listByUserId(Long userId) {
		return this.listByUserId(userId, false, false);
	}

	@Override
	public List<Permission> listByUserId(Long userId, boolean orderByCondition) {
		return this.listByUserId(userId, orderByCondition, false);
	}

	@Override
	public List<Permission> listByUserId(Long userId, boolean orderByCondition, boolean isAsc) {
		return permissionMapper.listByUserId(
				Wrappers.<Permission>query()
						.eq("ru.userId", userId)
						.orderBy(orderByCondition, isAsc, "p.sort"));
	}

	@Override
	public List<Permission> listAll() {
		return permissionMapper.selectList(
				Wrappers.<Permission>query()
						.select(Permission.class, d -> !d.getColumn().equals("createTime") && !d.getColumn().equals("updateTime"))
						.orderBy(true, true, "sort"));
	}
}
