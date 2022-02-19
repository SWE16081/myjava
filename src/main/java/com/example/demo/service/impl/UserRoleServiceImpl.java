package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.UserRole;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,UserRole> implements UserRoleService {
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void add(UserRole userRole) {
        //添加多个角色
        String[] roleId=userRole.getRoleId().split(",");
        Collection coll=new ArrayList();
        for(String id:roleId){
            UserRole role2=new UserRole();
            role2.setRoleId(id);
            role2.setUserId(userRole.getUserId());
            coll.add(role2);
        }
        boolean res=saveBatch(coll);
        if(res==false){
            throw new BusinessException("此用户添加角色失败");
        }
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void myUpdate(UserRole userRole) {
        //删除原有用户角色关系
        QueryWrapper<UserRole> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userRole.getUserId());
        boolean res=remove(queryWrapper);
        if(res==false){
            throw new BusinessException("删除原有角色失败");
        }
        //添加新的用户角关系
        String[] roleId=userRole.getRoleId().split(",");
        Collection coll=new ArrayList();
        for(String id:roleId){
            UserRole role2=new UserRole();
            role2.setRoleId(id);
            role2.setUserId(userRole.getUserId());
            coll.add(role2);
        }
        boolean res2=saveBatch(coll);
        if(res2==false){
            throw new BusinessException("用户更新角色失败");
        }
    }
}
