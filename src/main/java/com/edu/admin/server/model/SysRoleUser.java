package com.edu.admin.server.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleUser {

	@TableField("userId")
	private Long userId;
	@TableField("roleId")
	private Long roleId;

}
