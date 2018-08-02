package com.example.appjava.Dao;

import com.example.appjava.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsDao {
    public List<News> getNews();
}
