package com.edu.admin.education.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ArtGuestInfo实体",description="ArtGuestInfo实体")
public class ArtGuestInfoCommand {

    /**
     * 联系人
     */
    @NotNull(message = "联系人不能为空")
    @NotBlank(message = "联系人不能为空")
    @ApiModelProperty(value="联系人 必填", name="name", required=true, example="龙武")
    private String name;

    /**
     * 所在地
     */
    @ApiModelProperty(value="所在地", name="address", example="所在地")
    private String address;

    /**
     * 电话
     */
    @NotNull(message = "联系人不能为空")
    @NotBlank(message = "联系人不能为空")
    @ApiModelProperty(value="电话 必填", name="phone", required=true, example="电话")
    private String phone;

    @ApiModelProperty(value="电子邮件", name="email", example="电子邮件")
    private String email;

    /**
     * 留言
     */
    @ApiModelProperty(value="留言", name="message", example="客户留言")
    private String message;


}