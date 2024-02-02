package com.project.blog.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class AutoGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql:///blog";
        String username = "root";
        String password = "676402";
        String outputDir = "D:\\Vue\\vue_blog\\blog\\src\\main\\java";
        String basePackage = "com.project";
        String moduleName = "blog";
        String mapperLocation = "D:\\Vue\\vue_blog\\blog\\src\\main\\resources\\mapper\\" + moduleName;
        String tableName = "sys_role,sys_role_menu";
        //String tablePrefix = "_";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("layne") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName); // 设置需要生成的表名
                            //.addTablePrefix(tablePrefix); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
