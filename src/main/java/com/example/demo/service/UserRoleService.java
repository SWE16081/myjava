package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.bean.Role;
import com.example.demo.bean.UserRole;

public interface UserRoleService extends IService<UserRole> {
    public void add(UserRole userRole);
    public void myUpdate(UserRole userRole);
}
