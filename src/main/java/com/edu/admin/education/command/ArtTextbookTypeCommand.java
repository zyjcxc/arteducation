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
@ApiModel(value="ArtTextbookType实体",description="ArtTextbookType实体")
public class ArtTextbookTypeCommand {

    /**
     * 教材分类名
     */
    @NotNull(message = "教材分类名不能为空")
    @NotBlank(message = "教材分类名不能为空")
    @ApiModelProperty(value="教材分类名", name="name", required=true, example="电子琴")
    private String name;

    /**
     * 状态 0.草稿 1 正常 2删除
     */
    @ApiModelProperty(value="状态 0.草稿 1 正常 2删除", name="state", example="1")
    private String state;

}