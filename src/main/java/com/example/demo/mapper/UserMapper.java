package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.bean.UserRoleList;
import com.example.demo.bean.UserRoleList2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from users where id = #{id}")
    public User getUser2(Integer id);
    public User getUser(Integer id);
    IPage<UserRoleList2> selUserRole(Page<?> page, @Param("res") Map map);

}
