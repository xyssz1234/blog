package com.project.blog.service.impl;

import com.project.blog.entity.User;
import com.project.blog.mapper.UserMapper;
import com.project.blog.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
