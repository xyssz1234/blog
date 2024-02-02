package com.project.blog.service;

import com.project.blog.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色菜单关系表 服务类
 * </p>
 *
 * @author layne
 * @since 2023-04-27
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {


    void removeByRoleId(Integer roleId);
}
