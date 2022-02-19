package com.example.demo.service.impl;


import com.example.demo.bean.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CityService")
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;
    public void insert(City city){
        try{
            cityMapper.insert(city);
        }catch (Exception $e){

        }

    }
    public City getList(Long id){
        return cityMapper.getList(id);
    }
    public void add(City city){
        cityMapper.insert(city);
    }
    public void update(City city){
//        cityMapper.update(city);
    }
    public void delete(Integer id){
        cityMapper.delete(id);
    }
}
