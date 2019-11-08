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
@ApiModel(value="ArtAuthbook实体",description="ArtAuthbook实体")
public class ArtAuthbookCommand {

    /**
     * 授权书名称
     */
    @NotNull(message = "授权书名称不能为空")
    @NotBlank(message = "授权书名称不能为空")
    @ApiModelProperty(value="授权书名称", name="title", required=true, example="授予：沈阳巧语口才皇姑校区")
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

}