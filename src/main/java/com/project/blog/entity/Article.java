package com.project.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value = "Article对象", description = "文章表")
@Data
public class Article extends BaseEntity {

  //  private static final long serialVersionUID = 1L;

//    @ApiModelProperty("id")
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("封面图")
    private String picture;

    @ApiModelProperty("所属栏目")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty("发布用户")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("浏览量")
    private Integer views;

//    @ApiModelProperty("创建时间")
//    private LocalDateTime createTime;
//
//    @ApiModelProperty("更新时间")
//    private LocalDateTime updateTime;
//
//    @ApiModelProperty("状态/已发布/取消发布")
//    private Integer status;


}
