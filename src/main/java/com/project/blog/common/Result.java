package com.project.blog.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static Result<?> success() {
        return new Result<>(200, "操作成功", null);
    }

    public static Result<?> success(Object data) {
        return new Result<>(200, "操作成功", data);
    }

    public static Result<?> success(String msg) {
        return new Result<>(200, msg, null);
    }

    public  Result success1(T data)
    {
        return new Result(200, "操作成功", data);
    }


    public static Result<?> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static Result<?> error() {
        return new Result<>(500, "系统错误，请联系管理员", null);
    }

    public static Result<?> error(String msg) {
        return new Result<>(500, msg, null);
    }


}
