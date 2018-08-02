package com.example.appjava.service;


import com.example.appjava.Dao.UserDao;
import com.example.appjava.constant.CommonCode;
import com.example.appjava.domain.User;
import com.example.appjava.exception.CommonException;
import com.example.appjava.exception.ValidateException;
import com.example.appjava.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApiLoginService {
    @Autowired
    UserDao userDao;

    public User login(User user) throws ValidateException {
        User queryUser = userDao.getUserByName(user.getUserName());
        ValidateUtil.validate(user);
        System.out.println(queryUser.getPassword());
        System.out.println(user.getPassword());
        System.out.println(queryUser.getPassword().equals(user.getPassword()));
        if (queryUser == null || !queryUser.getPassword().equals(user.getPassword())) {
            throw new CommonException(CommonCode.INABLEUSER.getCode(), CommonCode.INABLEUSER.getMessage());
        }
        queryUser.setToken(UUID.randomUUID().toString());
        queryUser.setLoginTime(System.currentTimeMillis());
        userDao.updateUser(queryUser);
        return queryUser;
    }
}
