package com.edu.admin.server.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_permission")
public class SysRolePermission {

	@TableField("permissionId")
	private Long permissionId;
	@TableField("roleId")
	private Long roleId;

}
