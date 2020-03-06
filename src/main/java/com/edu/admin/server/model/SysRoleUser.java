package com.edu.admin.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleUser {

	private Long userId;
	private Long roleId;

}
