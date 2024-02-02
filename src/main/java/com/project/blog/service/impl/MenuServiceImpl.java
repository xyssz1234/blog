package com.project.blog.service.impl;

import com.project.blog.entity.Menu;
import com.project.blog.mapper.MenuMapper;
import com.project.blog.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
