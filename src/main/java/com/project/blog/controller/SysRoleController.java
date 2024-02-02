package com.project.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.SysRoleDTO;
import com.project.blog.dto.SysRoleMenuDTO;
import com.project.blog.entity.Menu;
import com.project.blog.entity.SysRole;
import com.project.blog.entity.SysRoleMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author layne
 * @since 2023-04-27
 */
@Api(tags = "角色相关接口")
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController{
    @PostMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result<?> getCategoryByPage(@RequestBody SysRoleDTO sysRoleDTO) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();

        if(sysRoleDTO.getTitle()!=null && !"".equals(sysRoleDTO.getTitle()))
        {
            wrapper.like(SysRole::getTitle,sysRoleDTO.getTitle());
        }


        Page<SysRole> page = sysRoleService.page(new Page<>(sysRoleDTO.getPageNum(), sysRoleDTO.getPageSize()), wrapper);
        return Result.success(page);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有role")
    public Result<?> getRole() {
        List<SysRole> sysRoleList = sysRoleService.list();
        return Result.success(sysRoleList);
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加/修改Role")
    public Result<?> save(@Validated @RequestBody SysRole sysRole) {
        return Result.success(sysRoleService.saveOrUpdate(sysRole));
    }

    @PostMapping("/delBatch")
    @ApiOperation(value = "删除Role")
    public Result<?> delBatch(@RequestBody List<Integer> ids) {
        sysRoleService.removeByIds(ids);
        return Result.success("Delete Success");
    }

    @GetMapping("/getById")
    @ApiOperation(value = "根据id获取menu")
    public Result<?> getById(@RequestParam Integer id) {
       LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
       wrapper.eq(SysRoleMenu::getRoleId,id);

        List<SysRoleMenu> list = sysRoleMenuService.list(wrapper);
        List<Menu> menu=new ArrayList<>();

        list.forEach(item->{
             Menu byId= menuService.getById(item.getMenuId());
             menu.add(byId);
        });
        return Result.success(menu);
    }

    @PostMapping("/saveRoleMenu")
    public Result<?> saveRoleMenu(@Validated @RequestBody SysRoleMenuDTO sysRoleMenuDTO)
    {
        sysRoleMenuService.removeByRoleId(sysRoleMenuDTO.getRoleId());
        sysRoleMenuDTO.getMenuIdList().forEach(item->{
             SysRoleMenu sysRoleMenu=new SysRoleMenu();
             sysRoleMenu.setRoleId(sysRoleMenuDTO.getRoleId());
             sysRoleMenu.setMenuId(item);
             sysRoleMenuService.save(sysRoleMenu);
        });
        return Result.success("save menu success");
    }


}
