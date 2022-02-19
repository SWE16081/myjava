package com.example.demo.mapper;

import com.example.demo.bean.City;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * 当mapper增多同样的curd方法被使用多次因此封装一个父类BaseMapper 满足基本的curd功能
 * 如何封装自己的basemapper,以及BaseMapper方法如何映射到xml中的sql
 * 像
 */
@Repository
@Mapper
//public interface CityMapper extends BaseMapper<City>{
public interface CityMapper {
    @Transactional
    public void insert(City city);
    public City getList(Long id);
    public int delete(Integer id);
}
