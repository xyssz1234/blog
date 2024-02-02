package com.project.blog.vo;

import com.project.blog.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@ApiModel(value = "ArticleDetailVO对象", description = "ArticleDetailVO")
@Data
public class ArticleVO extends BaseEntity {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("封面图")
    private String picture;

    @ApiModelProperty("所属栏目")
    private Integer categoryId;

    @ApiModelProperty("发布用户")
    private String username;

    @ApiModelProperty("发布用户id")
    private Integer userId;

    @ApiModelProperty("浏览量")
    private Integer views;

    @ApiModelProperty("栏目名称")
    private String categoryName;
//
//    @ApiModelProperty("用户信息")
//    private SysUserListVO userListVO;

}
