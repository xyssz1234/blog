package com.project.blog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RoleType {
    ROLE_ADMIN( "ROLE_ADMIN","超级管理员"),
    ROLE_USER( "ROLE_USER","普通用户");

    @EnumValue
    private String key;

    @JsonValue
    private String name;

    RoleType(String key, String name) {
        this.key=key;
        this.name = name;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
