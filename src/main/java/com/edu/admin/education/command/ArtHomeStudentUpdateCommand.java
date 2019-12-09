package com.edu.admin.education.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="修改ArtHomeStudent入参",description="修改ArtHomeStudent入参")
public class ArtHomeStudentUpdateCommand extends ArtHomeStudentCommand {
    /**
	* 主键id
	*/
    @ApiModelProperty(value="主键id", required=true, name="id")
    @NotNull(message = "主键id不能为空")
    private Long id;

}