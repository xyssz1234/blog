package com.project.blog.mapper;

import com.project.blog.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 栏目表 Mapper 接口
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
