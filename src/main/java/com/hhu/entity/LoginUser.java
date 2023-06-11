package com.hhu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(description = "用户登录的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    @ApiModelProperty(value = "用户Id")
    private Integer userId;
    @ApiModelProperty(value = "用户名",example = "fishmoun",required = true)
    private String userName;
    @ApiModelProperty(value = "密码",example = "0123456",required = true)
    private String password;
}
