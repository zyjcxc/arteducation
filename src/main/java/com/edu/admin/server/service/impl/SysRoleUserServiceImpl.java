package com.edu.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.admin.server.dao.SysRoleUserMapper;
import com.edu.admin.server.model.SysRoleUser;
import com.edu.admin.server.service.SysRoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

	private static final Logger log = LoggerFactory.getLogger("SysRoleUserServiceImpl");

}
