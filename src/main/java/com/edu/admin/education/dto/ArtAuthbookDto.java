package com.edu.admin.education.dto;

import com.edu.admin.education.command.ArtAuthbookCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ArtAuthbookDto",description="ArtAuthbookDto")
public class ArtAuthbookDto extends ArtAuthbookCommand {

    @ApiModelProperty(value="创建用户")
    private Long createUserId;
    @ApiModelProperty(value="创建时间")
    private Date createtime;
    @ApiModelProperty(value="更新时间")
    private Date updatetime;
    @ApiModelProperty(value="主键id", name="id")
    private Long id;


}