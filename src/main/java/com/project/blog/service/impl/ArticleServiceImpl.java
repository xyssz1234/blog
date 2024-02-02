package com.project.blog.service.impl;

import com.project.blog.entity.Article;
import com.project.blog.mapper.ArticleMapper;
import com.project.blog.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author layne
 * @since 2023-04-19
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
