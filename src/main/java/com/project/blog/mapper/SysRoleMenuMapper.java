package com.project.blog.mapper;

import com.project.blog.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色菜单关系表 Mapper 接口
 * </p>
 *
 * @author layne
 * @since 2023-04-27
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    public void  deleteByRoleId(Integer roleId);
}
