package com.project.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SysRoleDTO extends PageInfo{
    @ApiModelProperty("标题")
    @NotBlank(message = "title can't be null")
    private String title;
}
