package com.example.demo.controller.admin;


import com.example.demo.bean.City;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ExceptionCode;
import com.example.demo.service.CityService;
import com.example.demo.tools.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/admin")
public class CityController {
    @Autowired
    CityService cityService;
    @PostMapping("/city-add")
    public ApiMessage insert(@RequestBody City city){
        try{
            cityService.add(city);
            return ApiMessage.success("添加成功");
        }catch (BusinessException e){
            throw new BusinessException("城市添加失败"+e.getMessage());
        } catch (DataAccessException e){
            //捕捉sql异常
            SQLException se = (SQLException)e.getCause();
            throw new BusinessException("城市添加失败"+se);
//            throw new BusinessException(ExceptionCode.HTTP_ERROR);
        }
    }
    @PostMapping("/city-list")
    public City list(){
        return cityService.getList((long) 1);
    }
    @PostMapping("/city-update")
    public void update(@RequestBody City city){
//        cityService.insert(city);
        cityService.update(city);
    }
    @PostMapping("/city-del")
    public ApiMessage delete() {
        try {
            cityService.delete(11111);
            return ApiMessage.success("删除成功");
        } catch (BusinessException e) {
            throw new BusinessException("城市删除失败" + e.getMessage());
        } catch (DataAccessException e) {
            //捕捉sql异常
            SQLException se = (SQLException) e.getCause();
            throw new BusinessException("城市删除失败" + se);

        }
    }
}
