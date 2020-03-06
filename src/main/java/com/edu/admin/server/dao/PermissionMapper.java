package com.edu.admin.server.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.edu.admin.server.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select distinct p.* from sys_permission p" +
            " inner join sys_role_permission rp on p.id = rp.permissionid " +
            " inner join sys_role_user ru on ru.roleid = rp.roleid " +
            " ${ew.customSqlSegment} ")
//            "where ru.userid = 5 " +
//            "order by p.sort")
    List<Permission> listByUserId(@Param(Constants.WRAPPER)Wrapper<Permission> wrapper);
}
