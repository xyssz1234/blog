package com.project.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.MenuDTO;
import com.project.blog.entity.Menu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Api(tags = "菜单相关接口")
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController{
    @PostMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result<?> getCategoryByPage(@RequestBody MenuDTO menuDTO) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();

        if(menuDTO.getName()!=null && !"".equals(menuDTO.getName()))
        {
            wrapper.like(Menu::getName,menuDTO.getName());
        }


        Page<Menu> page = menuService.page(new Page<>(menuDTO.getPageNum(), menuDTO.getPageSize()), wrapper);
        return Result.success(page);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有menu")
    public Result<?> getRole() {
        List<Menu> sysRoleList = menuService.list();
        return Result.success(sysRoleList);
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加/修改menu")
    public Result<?> save(@Validated @RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
    }

    @PostMapping("/delBatch")
    @ApiOperation(value = "删除menu")
    public Result<?> delBatch(@RequestBody List<Integer> ids) {
        menuService.removeByIds(ids);
        return Result.success("Delete Success");
    }
}
