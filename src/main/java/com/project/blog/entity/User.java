package com.project.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@ApiModel(value = "User对象", description = "用户信息表")
@Getter
@Setter
@TableName("user")
public class User extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3,max=100,message = "用户名长度需要在3和50之间")
    private String username;

    @ApiModelProperty("密码")
//    @NotBlank(message = "密码不能为空")
//    @Length(min = 3,max=100,message = "密码长度需要在3和100之间")
    private String password;

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
   // private RoleType roleType;

    @ApiModelProperty("文章总浏览量")
    private Integer views;

    @ApiModelProperty("加盐")
    private String salt;

//    @ApiModelProperty("创建时间")
//    private Date createTime;
//
//    @ApiModelProperty("更新时间")
//    @TableField("")
//    private Date updateTime;

//    @ApiModelProperty("状态 已封禁/已删除/正常")
//    private Integer status;


}
