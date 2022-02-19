package com.example.demo.mapper;



import com.example.demo.bean.User2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface User2Mapper {
    public User2 getList(Long id);
    @Select("select * from users where id = #{id}")
    public User2 getUser2(Long id);
    public void insert(User2 user);
}
