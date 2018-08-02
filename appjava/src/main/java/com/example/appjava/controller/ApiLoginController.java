package com.example.appjava.controller;

import com.example.appjava.domain.User;
import com.example.appjava.exception.ValidateException;
import com.example.appjava.service.ApiLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import responseVo.ResponseVoUtil;

@RestController
public class ApiLoginController {

    @Autowired
    ApiLoginService apiLoginService;

    @ResponseBody
    @PostMapping(value = "/public/api/login", produces = "application/json;charset=utf-8")
    public String add(@RequestBody User user) throws ValidateException {
        User loginUser = apiLoginService.login(user);
        return ResponseVoUtil.success(loginUser, "登录成功", "000000");
    }
}
