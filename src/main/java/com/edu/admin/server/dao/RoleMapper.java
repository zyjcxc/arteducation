package com.edu.admin.server.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.edu.admin.server.model.Permission;
import com.edu.admin.server.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId ${ew.customSqlSegment}")
    // where ru.userId = #{userId}
    List<Role> listByUserId(@Param(Constants.WRAPPER)Wrapper<Permission> wrapper);
}
