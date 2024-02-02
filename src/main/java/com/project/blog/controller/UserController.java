package com.project.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.UserLoginDTO;
import com.project.blog.dto.UserQuery;
import com.project.blog.entity.Menu;
import com.project.blog.entity.SysRole;
import com.project.blog.entity.SysRoleMenu;
import com.project.blog.entity.User;
import com.project.blog.util.JwtUtils;
import com.project.blog.util.Utils;
import com.project.blog.vo.SysUserListVO;
import com.project.blog.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @GetMapping("/all")
    @ApiOperation(value = "获取所有用户")
    public Result<?> getUser() {
        List<User> user = userService.list();
        return Result.success(user);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取用户信息")
    public Result<?> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result<?> getUserByPage(@RequestBody UserQuery userQuery) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //wrapper.orderByDesc(User::getId);
        if (!"".equals(userQuery.getUsername()) && userQuery.getUsername() != null) {
            wrapper.like(User::getUsername, userQuery.getUsername());
        }
        Page<User> page = userService.page(new Page<>(userQuery.getPageNum(), userQuery.getPageSize()), wrapper);

        Page<SysUserListVO> sysUserListVOPage = new Page<>();
        BeanUtil.copyProperties(page, sysUserListVOPage);

        List<User> records = page.getRecords();
        List<SysUserListVO> listVOList = new ArrayList<>();

        records.forEach(item -> {
            SysUserListVO sysUserListVO = new SysUserListVO();
            String roleType = item.getRoleType();

            LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysRole::getRole, item.getRoleType())
                    .last("limit 1");

            SysRole sysRole = sysRoleService.getOne(queryWrapper);

            if (sysRole != null) {
                sysUserListVO.setRoleTypeText(sysRole.getTitle());
            }

            BeanUtil.copyProperties(item, sysUserListVO);
            listVOList.add(sysUserListVO);

        });
        sysUserListVOPage.setRecords(listVOList);
        return Result.success(sysUserListVOPage);
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加/修改用户")
    public Result<?> save(@Validated @RequestBody User user) {
        if (user.getId() != null) {
            user.setPassword(null);
        }
        return Result.success(userService.saveOrUpdate(user));
    }

    @PostMapping("/delBatch")
    @ApiOperation(value = "删除用户")
    public Result<?> delBatch(@RequestBody List<Integer> ids) {
        userService.removeByIds(ids);
        return Result.success("Delete Success");
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result<?> login(@RequestBody UserLoginDTO userLogin) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userLogin.getUsername())
                //.eq(User::getPassword,userLogin.getPassword())
                .last("limit 1");

        User user = userService.getOne(queryWrapper);
        if (user != null) {
            //拿到盐值和密码
            String salt = user.getSalt();
            String password = user.getPassword();
            if (!SecureUtil.md5(userLogin.getPassword() + salt).equals(password)) {
                return Result.error("please check username or password");
            }


            LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysRole::getRole, user.getRoleType())
                    .last("limit 1");
            SysRole sysRole = sysRoleService.getOne(wrapper);
            // List<SysRoleMenu> roleMenusList=new ArrayList<>();
            List<Menu> menuList = new ArrayList<>();
            if (sysRole != null) {
                LambdaQueryWrapper<SysRoleMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(SysRoleMenu::getRoleId, sysRole.getId());
                List<SysRoleMenu> roleMenusList = sysRoleMenuService.list(lambdaQueryWrapper);

                roleMenusList.forEach(item -> {
                    LambdaQueryWrapper<Menu> menuQueryWrapper = new LambdaQueryWrapper<Menu>();
                    menuQueryWrapper.eq(Menu::getId, item.getMenuId()).last("limit 1");
                    menuList.add(menuService.getOne(menuQueryWrapper));
                });

            }

            String token = JwtUtils.generateToken(user);
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("id", user.getId());
            map.put("username", user.getUsername());
            map.put("email", user.getEmail());
            map.put("motto", user.getMotto());
            map.put("mobile", user.getMobile());
            map.put("roleType", user.getRoleType());
            map.put("menuList", menuList);
            return Result.success(map);
        } else {
            return Result.error("please check username or password");
        }
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @Transactional
    public Result<?> register(@RequestBody UserLoginDTO userLogin) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userLogin.getUsername())
                .last("limit 1");

        User user = userService.getOne(queryWrapper);
        if (user != null) {
            return Result.error("用户名重复");
        } else {
            User userInfo = new User();
            BeanUtil.copyProperties(userLogin, userInfo);

            //密码加密
            String salt = Utils.salt();
            userInfo.setSalt(salt);
            //userInfo.setPassword(userInfo.getPassword());
            userInfo.setPassword(SecureUtil.md5(userInfo.getPassword() + salt));
            //todo
            userInfo.setRoleType("ROLE_USER");
            userService.save(userInfo);
            return Result.success("Register Success");

        }
    }






}
