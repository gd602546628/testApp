package com.example.appjava.Dao;

import com.example.appjava.annonation.StringParamAnnonation;
import com.example.appjava.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public User getUserByName(String userName);

    public void updateUser(User user);

    public User getUserByToken(String token);
}
