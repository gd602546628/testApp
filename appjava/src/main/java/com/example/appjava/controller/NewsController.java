package com.example.appjava.controller;

import com.alibaba.fastjson.JSON;
import com.example.appjava.domain.News;
import com.example.appjava.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import responseVo.PageResponseUtil;
import responseVo.ResponseVoUtil;

import java.util.List;
import java.util.Map;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;

    @ResponseBody
    @PostMapping(value = "/public/api/getNews", produces = "application/json;charset=utf-8")
    public String getNews(@RequestBody String params) {
        Map paramsMap = JSON.parseObject(params, Map.class);
        PageHelper.startPage(Integer.parseInt(paramsMap.get("pageNum").toString()), Integer.parseInt(paramsMap.get("pageSize").toString()));
        List<News> newsList = newsService.getNews();
        PageInfo<News> newsPageInfo = new PageInfo<>(newsList);
        return ResponseVoUtil.success(PageResponseUtil.success(newsPageInfo), "获取新闻成功", "000000");
    }
}
