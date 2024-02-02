package com.project.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 栏目表
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@ApiModel(value = "Category对象", description = "栏目表")
@Data
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("id")
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;

    @ApiModelProperty("标题")
    @NotBlank(message = "title can't be null")
    private String title;

//    @ApiModelProperty("创建时间")
//    private LocalDateTime createTime;
//
//    @ApiModelProperty("更新时间")
//    private LocalDateTime updateTime;

//    @ApiModelProperty("状态")
//    private Integer status;


}
