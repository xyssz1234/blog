package com.project.blog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserListVO {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("座右铭")
    private String motto;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String mobile;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色类型")
    private String roleType;

    @ApiModelProperty("角色类型描述")
    private String roleTypeText;

    @ApiModelProperty("文章总浏览量")
    private Integer views;

    @ApiModelProperty("加盐")
    private String salt;



}
