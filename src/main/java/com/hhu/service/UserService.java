package com.hhu.service;

import com.hhu.entity.LoginUser;
import com.hhu.entity.User;
import com.hhu.utils.BaseResponse;

public interface UserService {
    BaseResponse<LoginUser> checkLogin(String userName, String password);

    BaseResponse<User> register(User user);
     //将user.dat里面的数据加载到数据库中
    boolean importUsers();
}
