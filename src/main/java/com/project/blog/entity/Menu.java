package com.project.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Data
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu extends BaseEntity {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("父级id")
    private Integer pid;

    @ApiModelProperty("页面路径")
    private String pagePath;

    @ApiModelProperty("排序")
    private Integer sortNum;

//    @ApiModelProperty("创建时间")
//    private LocalDateTime createTime;
//
//    @ApiModelProperty("更新时间")
//    private LocalDateTime updateTime;
//
//    @ApiModelProperty("状态")
//    private Integer status;


}
