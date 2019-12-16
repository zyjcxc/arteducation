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
@ApiModel(value="ArtTextbook实体",description="ArtTextbook实体")
public class ArtTextbookCommand {

    /**
     * 教材分类id
     */
    @ApiModelProperty(value="教材分类id", name="textbookTypeId", required=true, example="1")
    private Long textbookTypeId;

    /**
     * 教材名
     */
    @NotNull(message = "教材名不能为空")
    @NotBlank(message = "教材名不能为空")
    @ApiModelProperty(value="教材名", name="title", required=true, example="电子琴（业余）考级教程7-9级")
    private String title;

    /**
     * 作者
     */
    @ApiModelProperty(value="作者", name="author", example="中央音乐学院考级委员会")
    private String author;

    /**
     * 教材版本
     */
    @ApiModelProperty(value="教材版本", name="author", example="第七级-第九级")
    private String version;

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