package com.edu.admin.server.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@TableName("sys_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity<Long> {

	private static final long serialVersionUID = 6180869216498363919L;

	@TableField("parentId")
	private Long parentId;
	private String name;
	private String css;
	private String href;
	private Integer type;
	private String permission;
	private Integer sort;

	@TableField(exist = false)
	private List<Permission> child;
}
