package com.project.blog.service.impl;

import com.project.blog.entity.Category;
import com.project.blog.mapper.CategoryMapper;
import com.project.blog.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 栏目表 服务实现类
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
