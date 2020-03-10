package com.edu.admin.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.server.dao.RoleMapper;
import com.edu.admin.server.dto.RoleDto;
import com.edu.admin.server.model.Permission;
import com.edu.admin.server.model.Role;
import com.edu.admin.server.model.SysRolePermission;
import com.edu.admin.server.model.SysRoleUser;
import com.edu.admin.server.page.table.OrderByObject;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;
import com.edu.admin.server.service.RoleService;
import com.edu.admin.server.service.SysRolePermissionService;
import com.edu.admin.server.service.SysRoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	private static final Logger log = LoggerFactory.getLogger("RoleServiceImpl");

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private SysRolePermissionService sysRolePermissionService;

	@Autowired
	private SysRoleUserService sysRoleUserService;

	@Override
	@Transactional
	public void saveRole(RoleDto roleDto) {
		Role role = roleDto;

		if (role.getId() != null) {// 修改
//			Role r = roleDao.getRole(role.getName());
			Role r = roleMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getName, role.getName()));
			if (r != null && !r.getId().equals(role.getId())) {
				throw new IllegalArgumentException(role.getName() + "已存在");
			}
//			roleDao.update(role);
			roleMapper.updateById(role);
		} else {// 新增
//			Role r = roleDao.getRole(role.getName());
			// @Select("select * from sys_role t where t.name = #{name}")
			Role r = roleMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, role.getName()));
			if (r != null) {
				throw new IllegalArgumentException(role.getName() + "已存在");
			}
//			roleDao.save(role);
			roleMapper.insert(role);
			log.debug("新增角色{}", role.getName());
		}

		saveRolePermission(role.getId(), roleDto.getPermissionIds());
	}

	private void saveRolePermission(Long roleId, List<Long> permissionIds) {
//		@Delete("delete from sys_role_permission where roleId = #{roleId}")
//		roleMapper.deleteRolePermission(roleId);
		sysRolePermissionService.remove(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getRoleId, roleId));

		permissionIds.remove(0L);
		if (!CollectionUtils.isEmpty(permissionIds)) {
//			roleDao.saveRolePermission(roleId, permissionIds);
			sysRolePermissionService.saveBatch(
					permissionIds.stream().map(permissionId -> new SysRolePermission(permissionId, roleId))
							.collect(Collectors.toList())
			);
		}
	}

	@Override
	@Transactional
	public void deleteRole(Long id) {
//		roleDao.deleteRolePermission(id);
		sysRolePermissionService.remove(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getRoleId, id));
		// @Delete("delete from sys_role_user where roleId = #{roleId}")
//		roleDao.deleteRoleUser(id);
		sysRoleUserService.remove(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getRoleId, id));
//		roleDao.delete(id);
		roleMapper.deleteById(id);

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
		Page<Role> page = new Page<>(request.getCurrentPage(),request.getLimit());
		Page<Role> rolePage = roleMapper.selectPage(page, makeQueryConditionWrapper(request));
		return new PageTableResponse((int)rolePage.getTotal(), (int)rolePage.getTotal(), rolePage.getRecords());
	}

	private QueryWrapper<Role> makeQueryConditionWrapper(PageTableRequest request) {
		OrderByObject orderByObject = request.getOrderByObject();
		QueryWrapper<Role> query = Wrappers.query();
		Map<String, Object> params = request.getParams();
		query.like(params.containsKey(Role.Column.NAME.key()), Role.Column.NAME.key(), params.get(Role.Column.NAME.key()));
		query.orderBy(orderByObject.isOrderBy(), orderByObject.isAsc(), orderByObject.getColumn());
		return query;
	}

}
