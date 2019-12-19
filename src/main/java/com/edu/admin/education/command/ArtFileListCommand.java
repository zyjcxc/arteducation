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
@ApiModel(value="ArtFileList实体",description="ArtFileList实体")
public class ArtFileListCommand {

    /**
     * 文件下载地址
     */
    @NotNull(message = "文件下载地址不能为空")
    @NotBlank(message = "文件下载地址不能为空")
    @ApiModelProperty(value="文件下载地址", name="url", required=true, example="http://www.baidu.com/1.doc")
    private String url;

    /**
     * 简介
     */
    @NotNull(message = "简介不能为空")
    @NotBlank(message = "简介不能为空")
    @ApiModelProperty(value="简介", name="content", required=true, example="1992年获市三好学生")
    private String content;


    /**
     * 标题
     */
    @NotNull(message = "标题不能为空")
    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value="标题", name="title", example="报名表2018")
    private String title;


}