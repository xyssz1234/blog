package com.project.blog.config;

import com.project.blog.interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

//@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Resource
    LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor).addPathPatterns(
                "/**"
        ).excludePathPatterns(
                "/user/login",
//                "/article/page",
//                "/article/detail",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/images/**",
                "/webjars/**",
                "/v2/api-docs",
                "/configuration/ui",
                "/configuration/security",
                "/doc.html"
        );

    }

}
