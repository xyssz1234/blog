package com.project.blog.mapper;

import com.project.blog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
