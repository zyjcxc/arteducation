package com.edu.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.server.dao.SysRolePermissionMapper;
import com.edu.admin.server.model.SysRolePermission;
import com.edu.admin.server.service.SysRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

	private static final Logger log = LoggerFactory.getLogger("SysRolePermissionServiceImpl");

}
