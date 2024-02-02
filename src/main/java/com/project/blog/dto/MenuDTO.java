package com.project.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MenuDTO extends PageInfo{
    @ApiModelProperty("菜单名")
    @NotBlank(message = "name can't be null")
    private String name;
}
