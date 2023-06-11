package com.hhu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhu.constant.Occupation;
import com.hhu.entity.LoginUser;
import com.hhu.entity.User;
import com.hhu.mapper.UserMapper;
import com.hhu.service.UserService;
import com.hhu.utils.BaseResponse;
import com.hhu.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public BaseResponse<LoginUser> checkLogin(String userName, String password) {
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("password", password);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null)
            return ResultUtils.error(400,"账号或密码错误!");
        int userId = user.getUserId();
        LoginUser loginUser = new LoginUser(userId,userName,password);
        return ResultUtils.success(loginUser);
    }

    @Override
    public BaseResponse<User> register(User user) {
        System.out.println(user.toString());
        String userName = user.getUserName();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User validateuser = userMapper.selectOne(queryWrapper);
        if(validateuser!= null)
            return ResultUtils.error(400,"该用户名已经存在！");
        userMapper.insert(user);

        return ResultUtils.success(user);
    }


    //将user.dat里面的数据加载到数据库中
    @Override
    public boolean importUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/Data/dataset/ml-1m/users.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split("::");

                User user = new User();
                user.setUserId(Integer.parseInt(userInfo[0]));
                user.setGender(userInfo[1]=="M"?"男":"女");
                user.setAge(Integer.parseInt(userInfo[2]));
                user.setOccupation(Occupation.getChineseName(Integer.parseInt(userInfo[3])));

                //设置默认的账号和密码
                user.setUserName("trainuser");
                user.setPassword("123");

                userMapper.insert(user);

            }
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
