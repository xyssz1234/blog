package com.project.blog.controller;

import com.project.blog.service.impl.*;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    UserServiceImpl userService;

    @Resource
    CategoryServiceImpl categoryService;

    @Resource
    ArticleServiceImpl articleService;

    @Resource
    SysRoleServiceImpl sysRoleService;

    @Resource
    MenuServiceImpl menuService;


    @Resource
    SysRoleMenuServiceImpl sysRoleMenuService;

}
