package com.hhu.service.Impl;

import com.hhu.entity.User;
import com.hhu.service.RecommendService;
import com.hhu.service.UserService;
import com.hhu.utils.BaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Resource
    private UserService userService;

    @Test
    void checkLogin() {
        //1.登录成功
        String userName = "aka";
        String password = "0123456";
        // 调用 checkLogin 方法进行登录验证
        BaseResponse<User> response1 = userService.checkLogin(userName, password);
        // 打印结果
        assertEquals(6041,response1.getData().getUserId());

        //2.账号或密码错误
        String userName2 = "aka";
        String password2 = "012345";
        // 调用 checkLogin 方法进行登录验证
        BaseResponse<User> response2 = userService.checkLogin(userName2, password2);
        // 打印结果
        assertEquals(null,response2.getData());
        assertEquals("账号或密码错误!",response2.getMessage());

    }

    @Test
    void register() {
        User user = new User();
        user.setUserName("newuser");
        user.setPassword("123");
        user.setUserId(6042);
        user.setGender("男");
        user.setOccupation("教育");
        user.setAge(18);
        user.setLikes("科幻,喜剧");
        // 设置其他属性

        // 调用 register 方法进行用户注册
        BaseResponse<User> response = userService.register(user);

        // 打印结果
        System.out.println(response);
    }


//    @Test
//    void importUsers() {
//        // 调用 importUsers 方法将数据加载到数据库中
//        boolean result = userService.importUsers();
//
//        // 打印结果
//        System.out.println("Import result: " + result);
//    }
}