package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleService extends IService<Role> {
    public void add(Role role);
    public void myUpdate(Role role);
    public void myDelete(Integer id);
    public Page myPageList(Map map);
    IPage<Role> myPageList2(Map<String, Object> map);
}
