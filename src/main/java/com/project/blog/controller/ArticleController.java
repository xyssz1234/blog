package com.project.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.ArticleDTO;
import com.project.blog.dto.SysUserDTO;
import com.project.blog.entity.Article;
import com.project.blog.entity.Category;
import com.project.blog.entity.User;
import com.project.blog.enums.StatusType;
import com.project.blog.util.UserThreadLocal;
import com.project.blog.vo.ArticleDetailVO;
import com.project.blog.vo.ArticleVO;
import com.project.blog.vo.SysUserListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Api(tags = "文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController extends  BaseController{

    @PostMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result<?> getCategoryByPage(@RequestBody ArticleDTO articleDTO) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        if(articleDTO.getTitle()!=null && !"".equals(articleDTO.getTitle()))
        {
            wrapper.like(Article::getTitle,articleDTO.getTitle());
        }

        SysUserDTO currentUser= UserThreadLocal.getCurrentUser();

//        if(currentUser != null){
//             if(!RoleType.ROLE_ADMIN.equals(currentUser.getRoleType())){
//                 wrapper.eq(Article::getUserId,currentUser.getId());
//             }
//        }

        Page<Article> page = articleService.page(new Page<>(articleDTO.getPageNum(), articleDTO.getPageSize()), wrapper);

        Page<ArticleVO> voPage = new Page<>();

        List<Article> records = page.getRecords();
        List<ArticleVO> voList = new ArrayList<>();
        BeanUtil.copyProperties(page, voPage);
        records.forEach(item -> {
            ArticleVO articleVO = new ArticleVO();
            BeanUtil.copyProperties(item, articleVO);

            Category category = categoryService.getById(articleVO.getCategoryId());
            if (category != null) {
                articleVO.setCategoryName(category.getTitle());
            } else {
                articleVO.setCategoryName("未知栏目");
            }
            User user= userService.getById(articleVO.getUserId());
            if (user != null) {
                articleVO.setUsername(user.getUsername());
            } else {
                articleVO.setUsername("未知用户");
            }

            voList.add(articleVO);

        });
        voPage.setRecords(voList);

//        if(currentUser != null){
//            return Result.success(voPage);
//        }else{
//            return Result.success();
//        }

        return Result.success(voPage);

    }

    @PostMapping("/uPage")
    @ApiOperation(value = "前端分页查询")
    public Result<?> uPage(@RequestBody ArticleDTO articleDTO) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, StatusType.Yes);
        if(articleDTO.getTitle()!=null && !"".equals(articleDTO.getTitle()))
        {
            wrapper.like(Article::getTitle,articleDTO.getTitle());
        }
        Page<Article> page = articleService.page(new Page<>(articleDTO.getPageNum(), articleDTO.getPageSize()), wrapper);

//        Page<ArticleVO> voPage = new Page<>();
//
//        List<Article> records = page.getRecords();
//        List<ArticleVO> voList = new ArrayList<>();
//        BeanUtil.copyProperties(page, voPage);
//        records.forEach(item -> {
//            ArticleVO articleVO = new ArticleVO();
//            BeanUtil.copyProperties(item, articleVO);
//
//            Category category = categoryService.getById(articleVO.getCategoryId());
//            if (category != null) {
//                articleVO.setCategoryName(category.getTitle());
//            } else {
//                articleVO.setCategoryName("未知栏目");
//            }

//            SysUser createUser = sysUserService.getById(articleVO.getCreateUserId());
//            SysUser updateUser = sysUserService.getById(articleVO.getUpdateUserId());
//            if (createUser != null) {
//                articleVO.setCreateUsername(createUser.getUsername());
//            } else {
//                articleVO.setCreateUsername("未知用户");
//            }
//            if (updateUser != null) {
//                articleVO.setUpdateUsername(updateUser.getUsername());
//            } else {
//                articleVO.setUpdateUsername("未知用户");
//            }
//            User user= userService.getById(articleVO.getUserId());
//            if (user != null) {
//                articleVO.setUsername(user.getUsername());
//            } else {
//                articleVO.setUsername("未知用户");
//            }
//
//            voList.add(articleVO);
//
//        });
//        voPage.setRecords(voList);
        return Result.success(page);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有Article")
    public Result<?> getCategory() {
        List<Article> articles = articleService.list();
        return Result.success(articles);
    }

    @GetMapping("/uDetail")
    @ApiOperation(value = "根据id获取Article")
    public Result<?> uDetail(@RequestParam("id") Integer id) {
        Article article = articleService.getById(id);
        if(article == null){
            return  Result.error("文章不存在");
        }

        if(article.getStatus().equals(StatusType.No)){
            return Result.error("文章被禁用");
        }
        article.setViews(article.getViews()+1);
        articleService.saveOrUpdate(article);

        ArticleDetailVO articleDetailVO=new ArticleDetailVO();
        BeanUtils.copyProperties(article, articleDetailVO);

        if(articleDetailVO.getCategoryId()!=null){
            Category category=categoryService.getById(articleDetailVO.getCategoryId());

            if(category != null){
                articleDetailVO.setCategoryText(category.getTitle());
            }else
            {
                articleDetailVO.setCategoryText("unknown category");
            }
        }else {
            articleDetailVO.setCategoryText("unknown category");
        }

        User user = userService.getById(articleDetailVO.getUserId());
        SysUserListVO sysUserListVO=new SysUserListVO();
        if(user!=null){
            BeanUtils.copyProperties(user, sysUserListVO);
        }else{
            sysUserListVO.setUsername("unknown user");
        }
        articleDetailVO.setUserListVO(sysUserListVO);



        return Result.success(articleDetailVO);
    }


    @PostMapping("/save")
    @ApiOperation(value = "添加/修改Article")
    public Result<?> save( @RequestBody Article article) {
        return Result.success(articleService.saveOrUpdate(article));
    }

    @PostMapping("/delBatch")
    @ApiOperation(value = "删除Article")
    public Result<?> delBatch(@RequestBody List<Integer> ids) {
        articleService.removeByIds(ids);
        return Result.success("Delete Success");
    }





}
