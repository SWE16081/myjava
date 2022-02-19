package com.example.demo.service;
import com.example.demo.bean.User2;
import com.example.demo.mapper.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService2 {
    @Autowired
    User2Mapper userMapper;
    public void insert(User2 user){
        userMapper.insert(user);
    }
    public User2 getUser(Long id){
       return  userMapper.getList(id);
    }

}
