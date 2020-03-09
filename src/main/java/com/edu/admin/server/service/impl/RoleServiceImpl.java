package com.edu.admin.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.server.dao.RoleDao;
import com.edu.admin.server.dao.RoleMapper;
import com.edu.admin.server.dto.RoleDto;
import com.edu.admin.server.model.Permission;
import com.edu.admin.server.model.Role;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import com.edu.admin.server.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	@Transactional
	public void saveRole(RoleDto roleDto) {
		Role role = roleDto;

		if (role.getId() != null) {// 修改
			Role r = roleDao.getRole(role.getName());
			if (r != null && r.getId() != role.getId()) {
				throw new IllegalArgumentException(role.getName() + "已存在");
			}

			roleDao.update(role);
		} else {// 新增
			Role r = roleDao.getRole(role.getName());
			if (r != null) {
				throw new IllegalArgumentException(role.getName() + "已存在");
			}

			roleDao.save(role);

			log.debug("新增角色{}", role.getName());
		}

		saveRolePermission(role.getId(), roleDto.getPermissionIds());
	}

	private void saveRolePermission(Long roleId, List<Long> permissionIds) {
		roleDao.deleteRolePermission(roleId);
		permissionIds.remove(0L);
		if (!CollectionUtils.isEmpty(permissionIds)) {
			roleDao.saveRolePermission(roleId, permissionIds);
		}
	}

	@Override
	@Transactional
	public void deleteRole(Long id) {
		roleDao.deleteRolePermission(id);
		roleDao.deleteRoleUser(id);
		roleDao.delete(id);

		log.debug("删除角色id:{}", id);
	}

	@Override
	public List<Role> listByUserId(Long userId) {
		return roleMapper.listByUserId(
				Wrappers.<Permission>query()
						.eq("ru.userId", userId));
	}


	@Override
	public PageTableResponse queryList(PageTableRequest request) {
		Page<Role> page = new Page<>(request.getOffset(),request.getLimit());
		Page<Role> rolePage = roleMapper.selectPage(page, null);
		return new PageTableResponse((int)rolePage.getTotal(), (int)rolePage.getTotal(), rolePage.getRecords());
	}

}
