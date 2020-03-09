package com.edu.admin.server.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@TableName("sys_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Role extends BaseEntity<Long> {

	private static final long serialVersionUID = -3802292814767103648L;

	private String name;

	private String description;

}
