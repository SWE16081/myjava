package com.example.demo.service;


import com.example.demo.bean.City;

public interface CityService {
    public void insert(City city);
    public City getList(Long id);
    public void add(City city);
    public void update(City city);

    public void delete(Integer id);
}
