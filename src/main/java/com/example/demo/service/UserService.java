package com.example.demo.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.bean.User;
import com.example.demo.bean.UserRoleList;
import com.example.demo.bean.UserRoleList2;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 继承mybatis IService父类 定义了一些列操作数据库方法
 */
public interface UserService extends IService<User> {
   public void add(User user);
   public Object login(User user);
   public User getUserById(Integer id);
   public IPage<UserRoleList2> myPageList(Map map);
}
