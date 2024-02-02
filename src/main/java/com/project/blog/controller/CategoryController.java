package com.project.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.CategoryDTO;
import com.project.blog.entity.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 栏目表 前端控制器
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Api(tags = "栏目相关接口")
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController{

    @PostMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result<?> getCategoryByPage(@RequestBody CategoryDTO categoryDTO) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();

        if(categoryDTO.getTitle()!=null && !"".equals(categoryDTO.getTitle()))
        {
            wrapper.like(Category::getTitle,categoryDTO.getTitle());
        }


        Page<Category> page = categoryService.page(new Page<>(categoryDTO.getPageNum(), categoryDTO.getPageSize()), wrapper);
        return Result.success(page);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有Category")
    public Result<?> getCategory() {
        List<Category> category = categoryService.list();
        return Result.success(category);
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加/修改Category")
    public Result<?> save(@Validated @RequestBody Category category) {
        return Result.success(categoryService.saveOrUpdate(category));
    }

    @PostMapping("/delBatch")
    @ApiOperation(value = "删除Category")
    public Result<?> delBatch(@RequestBody List<Integer> ids) {
        categoryService.removeByIds(ids);
        return Result.success("Delete Success");
    }

}
