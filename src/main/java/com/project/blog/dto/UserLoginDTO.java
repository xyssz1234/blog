package com.project.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDTO {

    @NotBlank(message = "username can't be null")
    @Length(min = 2,max=50,message = "username length should be in [2,50]")
    @ApiModelProperty("username")
    private String username;

    @NotBlank(message = "password can't be null")
    @Length(min = 4,max=100,message = "password length should be in [4,100]")
    @ApiModelProperty("password")
    private String password;

}
