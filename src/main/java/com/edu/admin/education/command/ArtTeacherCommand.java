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
@ApiModel(value="ArtTeacher实体",description="ArtTeacher实体")
public class ArtTeacherCommand {

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空")
    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value="名称", name="name", required=true, example="名称")
    private String name;

    @ApiModelProperty(value="姓名拼音", name="name", required=true, example="姓名拼音")
    private String namePy;

    /**
     * 名称
     */
    @ApiModelProperty(value="职称、职位", name="position", example="职称、职位")
    private String position;

    /**
     * 简介
     */
    @ApiModelProperty(value="简介", name="content", example="简介")
    private String content;

    /**
     * 照片url
     */
    @ApiModelProperty(value="照片url", name="photoUrl", example="www.baidu.com/1.jpg")
    private String photoUrl;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别 m 男 g女", name="photoUrl", example="m")
    private String sex;

    /**
     * 状态 1 正常 2删除
     */
    @ApiModelProperty(value="状态 1 正常 2删除", name="state", example="1")
    private String state;

}