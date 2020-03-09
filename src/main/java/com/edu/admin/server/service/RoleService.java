package com.edu.admin.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.admin.server.dto.RoleDto;
import com.edu.admin.server.model.Role;
import com.edu.admin.server.page.table.PageTableRequest;
import com.edu.admin.server.page.table.PageTableResponse;

import java.util.List;

public interface RoleService extends IService<Role> {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);

	/**
	 * 根据 用户id查询角色 mplus 改版
	 * @param userId
	 * @return
	 * @author mengqa
	 */
	List<Role> listByUserId(Long userId);

    PageTableResponse queryList(PageTableRequest request);

}
