package com.edu.admin.server.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@TableName("sys_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User extends BaseEntity<Long> {

	private static final long serialVersionUID = -6525908145032868837L;

	private String username;

	private String password;

	@JsonIgnore
	private String salt;
	private String nickname;
	@TableField("headImgUrl")
	private String headImgUrl;
	private String phone;
	private String telephone;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private Integer sex;
	private Integer status;

	public interface Status {
		int DISABLED = 0;
		int VALID = 1;
		int LOCKED = 2;
	}

	public enum Column {

		USERNAME("username"),
		NICKNAME("nickname"),
		STATUS("status"),

		;
		private String key;
		Column(String name) {
			this.key = name;
		}
		public String key() {
			return key;
		}
	}

}
