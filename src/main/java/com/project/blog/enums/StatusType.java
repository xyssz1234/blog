package com.project.blog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StatusType {
    No(0, "禁用"),

    Yes(1, "启用");

    @EnumValue
    private Integer key;

    @JsonValue
    private String name;

    StatusType(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
