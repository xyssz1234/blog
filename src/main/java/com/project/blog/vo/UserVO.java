package com.project.blog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVO {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("座右铭")
    private String motto;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("文章总浏览量")
    private Integer views;

}
