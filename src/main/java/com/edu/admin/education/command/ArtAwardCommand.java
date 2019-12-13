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
@ApiModel(value="ArtAward实体",description="ArtAward实体")
public class ArtAwardCommand {

    /**
     * 教材名
     */
    @NotNull(message = "奖项、证书名不能为空")
    @NotBlank(message = "奖项、证书名不能为空")
    @ApiModelProperty(value="奖项、证书名", name="title", required=true, example="全国艺术特长生认证证书内芯")
    private String title;

    /**
     * 简介
     */
    @ApiModelProperty(value="简介", name="content", example="简介")
    private String content;

    /**
     * 状态 0.草稿 1 正常 2删除
     */
    @ApiModelProperty(value="状态 0.草稿 1 正常 2删除", name="state", example="1")
    private String state;

    /**
     * 图片url
     */
    @ApiModelProperty(value="图片url", name="picurl", example="www.baidu.com")
    private String picurl;

}