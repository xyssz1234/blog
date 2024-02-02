package com.project.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author layne
 * @since 2023-04-27
 */
@Data
@TableName("sys_role")
@ApiModel(value = "SysRole对象", description = "角色表")
public class SysRole extends BaseEntity {

    @ApiModelProperty("角色名称")
    private String title;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("角色标识")
    private String role;




}
