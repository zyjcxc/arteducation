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
@ApiModel(value="ArtNews实体",description="ArtNews实体")
public class ArtNewsCommand {

    /**
     * 标题
     */
    @NotNull(message = "标题不能为空")
    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value="标题", name="title", required=true, example="新闻标题")
    private String title;

    /**
     * 作者
     */
    @ApiModelProperty(value="作者", name="author", example="作者")
    private String author;

    /**
     * 来源
     */
    @ApiModelProperty(value="来源", name="source", example="来源")
    private String source;

    @NotNull(message = "内容不能为空")
    @NotBlank(message = "内容不能为空")
    @ApiModelProperty(value="内容", name="from", required=true, example="内容")
    private String content;

    /**
     * 状态 0.草稿 1 正常 2删除
     */
    @ApiModelProperty(value="0.草稿 1 正常 2删除", name="state", example="1")
    private String state;

    /**
     * 1.新闻 2.公告
     */
    @ApiModelProperty(value="1.新闻 2.公告", name="type", required=true, example="1")
    private Integer type;


}