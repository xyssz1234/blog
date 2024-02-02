package com.project.blog.service.impl;

import com.project.blog.entity.SysRoleMenu;
import com.project.blog.mapper.SysRoleMenuMapper;
import com.project.blog.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单关系表 服务实现类
 * </p>
 *
 * @author layne
 * @since 2023-04-27
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {


    @Override
    public void removeByRoleId(Integer roleId) {
        this.baseMapper.deleteByRoleId(roleId);
    }
}
