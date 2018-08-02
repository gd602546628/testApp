package com.example.appjava.service;

import com.example.appjava.Dao.NewsDao;
import com.example.appjava.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    NewsDao newsDao;
    public List<News> getNews() {
        return newsDao.getNews();
    }
}
