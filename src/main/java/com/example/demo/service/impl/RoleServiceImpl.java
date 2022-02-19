package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Role;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Transactional(rollbackFor = BusinessException.class)
    public void add(Role role) {
        //判断同种类型下的角色是否重复
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.eq("role_name",role.getRoleName());
        queryWrapper.eq("type",role.getType());
        Role roleRes=getOne(queryWrapper);
        if(ObjectUtils.isEmpty(roleRes)){//判断查询返回的结果 实体类是否为空
           save(role);
        }else{
            throw new BusinessException("此角色已存在");
        }
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void myUpdate(Role role) {
        QueryWrapper<Role> queryWrapper=new QueryWrapper<Role>();
        //查询当前id的角色信息
        queryWrapper.eq("id",role.getId());
        Role roleRes=getOne(queryWrapper);
        if(!roleRes.getType().equals(role.getType())||!roleRes.getRoleName().equals(role.getRoleName())){
            QueryWrapper<Role> queryWrapper1=new QueryWrapper<Role>();
            queryWrapper1.eq("role_name",role.getRoleName());
            queryWrapper1.eq("type",role.getType());
            Role roleRes2=getOne(queryWrapper1);
            if(ObjectUtils.isEmpty(roleRes2)){//判断查询返回的结果 实体类是否为空
                update(role,queryWrapper);
            }else{
                throw new BusinessException("此角色已存在");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void myDelete(Integer id) {
        QueryWrapper<Role> queryWrapper=new QueryWrapper<Role>();
        queryWrapper.eq("id",id);
        boolean res=remove(queryWrapper);
        if(!res){
            throw new BusinessException("id不存在");
        }
    }

    @Override
    public Page myPageList(Map map) {
       Integer current= (Integer) map.get("pageNumber");
       Integer size= (Integer) map.get("pageSize");
       Page<Role> page = new Page<>(current,size);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        if(map.get("roleName")!=null){
            queryWrapper.eq("role_name",map.get("roleName"));
        }
        if(map.get("type")!=null){
            queryWrapper.eq("type",map.get("type"));
        }
       Page res= page(page,queryWrapper);

       return res;
    }

    @Override
    public IPage<Role> myPageList2(Map<String, Object> map) {
//    public List myPageList2(Map map) {
        Integer current= (Integer) map.get("pageNumber");
        Integer size= (Integer) map.get("pageSize");
        Page<Role> page = new Page<>(current,size);
//        String roleName = (String) map.get("roleName");
//        String type= (String) map.get("type");
//        HashMap<String,Object> map2 = new HashMap<String, Object>();
//        map.put("id", 1);
        return roleMapper.myPageList2(page,map);
    }
}
