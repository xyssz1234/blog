package com.project.blog.util;

import cn.hutool.crypto.SecureUtil;

import java.util.Random;

public class Utils {
    //超级管理员可访问接口
    private final static String[] ROLE_ADMIN = {

            //文件上传和预览下载
            "/file/uploads",
           "/file//views",
            "/file/wangeditorUploads",

            //文章管理
            "/article/delBatch",
            "/article/all",
            "/article/page",
            "/article/save",
            "/article/uDetail",
            "/article/uPage",

            //分类管理
            "/category/delBatch",
            "/category/all",
            "/category/page",
            "/category/save",

            //菜单管理
            "/menu/delBatch",
            "/menu/all",
            "/menu/page",
            "/menu/save",

            //用户管理
            "/user/delBatch",
            "/user/all",
            "/user/page",
            "/user/save",
            "/user/getById",

            //角色管理
            "/sysRole/delBatch",
            "/sysRole/all",
            "/sysRole/page",
            "/sysRole/save",
            "/sysRole/getById",
            "/sysRole/saveRoleMenu",


    };
    //普通用户可访问接口
    private final static String[] ROLE_USER = {

            //文件上传和预览下载
            "/file/uploads",
//            "/file/views",
            "file/wangeditorUploads",

            //文章管理
            "/article/delBatch",
//            "/article/list",
            "/article/page",
            "/article/save",
            "/article/uDetail",
            "/article/uPage",

            //分类管理
            "/category/all",

            //用户新增保存
            "/sysUser/save",


    };



    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 自定义简单生成盐，是一个随机生成的长度为16的字符串，每一个字符是随机的十六进制字符
     */
    public static String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(hex[random.nextInt(16)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String salt = salt();
        System.out.println(salt);
        //2e400f7fe59147f895338b4ee9011239
        System.out.println(SecureUtil.md5("getToken" + salt));
    }

    /***
     *
     * @param url
     * @return
     */
    public static Boolean isAdmin(String url){
        boolean isFlag = false;
        for (String s:ROLE_ADMIN) {
            if (url.contains(s)) {
                isFlag = true;
            }
        }
        return  isFlag;
    }
}
