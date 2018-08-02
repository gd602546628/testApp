package com.example.appjava.filters;

import com.alibaba.fastjson.JSONObject;
import com.example.appjava.Dao.UserDao;
import com.example.appjava.constant.CommonCode;
import com.example.appjava.domain.User;
import org.apache.shiro.web.filter.AccessControlFilter;
import responseVo.ResponseVoUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TokenFilter extends AccessControlFilter {
    UserDao userDao;

    public TokenFilter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token") != null ? httpServletRequest.getHeader("token") : httpServletRequest.getParameter("token");

        /*filter中读取流之后，后续controller拿不到数据*/

      /*  String param = null;

        *//*读取application/json 格式的请求*//*
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
        param = jsonObject.toJSONString();

        String token = jsonObject.getString("token");*/
        User user = userDao.getUserByToken(token);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(ResponseVoUtil.error(CommonCode.INABLETOKEN.getMessage(), CommonCode.INABLETOKEN.getCode()));
        return false;
    }
}
