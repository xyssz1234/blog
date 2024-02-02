package com.project.blog.service.impl;

import com.project.blog.entity.UserMenu;
import com.project.blog.mapper.UserMenuMapper;
import com.project.blog.service.IUserMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户菜单表 服务实现类
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Service
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenu> implements IUserMenuService {

}
