package com.project.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 角色菜单关系表
 * </p>
 *
 * @author layne
 * @since 2023-04-27
 */
@Data
@TableName("sys_role_menu")
@ApiModel(value = "SysRoleMenu对象", description = "角色菜单关系表")
public class SysRoleMenu extends BaseEntity {

//    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("id")
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;

    @ApiModelProperty("角色id")
    private Integer roleId;

    @ApiModelProperty("菜单id")
    private Integer menuId;




}
