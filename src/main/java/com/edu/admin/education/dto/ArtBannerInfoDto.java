package com.edu.admin.education.dto;

import com.edu.admin.education.command.ArtBannerInfoCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ArtBannerInfoDto",description="ArtBannerInfoDto")
public class ArtBannerInfoDto extends ArtBannerInfoCommand {

    @ApiModelProperty(value="创建时间")
    private Date createtime;
    @ApiModelProperty(value="更新时间")
    private Date updatetime;
    @ApiModelProperty(value="主键id", name="id")
    private Long id;


}