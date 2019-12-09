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
@ApiModel(value="ArtHomeStudent实体",description="ArtHomeStudent实体")
public class ArtHomeStudentCommand {

    /**
     * 图片连接
     */
    @NotNull(message = "图片连接不能为空")
    @NotBlank(message = "图片连接不能为空")
    @ApiModelProperty(value="图片连接", name="picurl", required=true, example="http://www.baidu.com/1.jpg")
    private String picurl;

    /**
     * 简介
     */
    @NotNull(message = "简介不能为空")
    @NotBlank(message = "简介不能为空")
    @ApiModelProperty(value="简介", name="content", required=true, example="1992年获市三好学生")
    private String content;

    /**
     * 顺序
     */
    @NotNull(message = "顺序不能为空")
    @ApiModelProperty(value="顺序", name="sort", example="1")
    private Integer sort;

    /**
     * 学生名
     */
    @ApiModelProperty(value="学生名", name="name", example="李一一")
    private String name;

    /**
     * 1.首页展示 0.首页不展示
     */
    @NotNull(message = "首页展示不能为空")
    @ApiModelProperty(value="1.首页展示 0.首页不展示", name="recommend", required=true, example="1")
    private Integer recommend;

}