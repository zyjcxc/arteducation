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
@ApiModel(value="ArtBannerInfo实体",description="ArtBannerInfo实体")
public class ArtBannerInfoCommand {

    /**
     * 位置 1.顶部 2.中部
     */
    @NotNull(message = "位置不能为空")
    @ApiModelProperty(value="位置 1.顶部 2.中部", name="site", required=true, example="1")
    private Integer site;

    /**
     * 图片连接
     */
    @NotNull(message = "图片连接不能为空")
    @NotBlank(message = "图片连接不能为空")
    @ApiModelProperty(value="图片连接", name="picurl", required=true, example="http://www.baidu.com/1.jpg")
    private String picurl;

    /**
     * 跳转url
     */
    @ApiModelProperty(value="跳转url", name="picurl", example="http://www.baidu.com/1.jpg")
    private String url;

    /**
     * 顺序
     */
    @NotNull(message = "banner顺序不能为空")
    @ApiModelProperty(value="banner顺序", name="sort", example="1")
    private Integer sort;

    /**
     * 状态 0.草稿 1 正常 2删除
     */
    @ApiModelProperty(value="状态 0.草稿 1 正常 2删除", name="state", example="1")
    private String state;

}