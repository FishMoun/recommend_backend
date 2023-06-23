package com.hhu.controller;

import com.hhu.entity.LoginUser;
import com.hhu.entity.User;
import com.hhu.service.UserService;

import com.hhu.utils.BaseResponse;
import com.hhu.utils.ResultUtils;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.Map;
@Api(tags = "用户管理",description = "提供用户登录、注册、添加基本信息等功能")
@RestController
public class UserController {

    @Resource
    UserService userService;
    @ApiOperation(value = "用户登录接口")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "登录成功！"),@ApiResponse(responseCode = "400",description = "账号或密码错误！")})
    @PostMapping("/login")
    public BaseResponse<User> login(@ApiParam(value= "用户名、密码",required = true)  @RequestBody LoginUser loginUser){
        String userName = loginUser.getUserName();
        String password = loginUser.getPassword();

        return userService.checkLogin(userName,password);
    }

    @ApiOperation(value = "用户注册接口")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "注册成功！"),@ApiResponse(responseCode = "400",description = "该用户名已经存在！")})
    @PostMapping("/register")
    public BaseResponse<User> register(@ApiParam(value= "用户名、密码、职业、年龄、性别、喜好(json里面的用户Id需要删除，后端自动生成)")  @RequestBody User user){
        return userService.register(user);
    }


    @ApiOperation(value = "导入训练集用户到数据库接口(后端使用)")
    @GetMapping ("/import")
    public Boolean login(){
        return userService.importUsers();
    }
}
