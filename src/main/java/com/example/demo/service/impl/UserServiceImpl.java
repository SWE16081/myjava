package com.example.demo.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.bean.UserRoleList;
import com.example.demo.bean.UserRoleList2;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.tools.MyMd5Tool;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 继承 实现父类IServic的ServiceImpl 具体
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {
     public void test(){
         this.save(new User("123","132"));
     }
     @Autowired
     TokenService tokenService;
//     @Resource
     @Resource
     UserMapper userMapper;
     //用户添加
    @Transactional(rollbackFor = BusinessException.class)
     public void add(User user){
         //判断账户唯一性
         QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
         queryWrapper.eq("name",user.getName());
         User userRes=getOne(queryWrapper);
         if(ObjectUtils.isEmpty(userRes)){//判断查询返回的结果 实体类是否为空
             user.setPassword(MyMd5Tool.getMd5(user.getPassword()));
             save(user);
         }else{
             throw new BusinessException("此账号已存在");
         }
     }
     //用户登录
     public Object login(User user){
         //判断帐号是否存在
         QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
         queryWrapper.eq("name",user.getName());
         User userRes=getOne(queryWrapper);
         Map<Object, Object> map = new HashMap<Object,Object>();
         if(ObjectUtils.isEmpty(userRes)){
             throw new BusinessException("帐号密码错误");
         }else{
             //判断密码是否正确
             if(!userRes.getPassword().equals(MyMd5Tool.getMd5(user.getPassword()))){
                 throw new BusinessException("帐号密码错误");
             }
             //创建token
             String token = tokenService.getToken(userRes);
             map.put("token",token);
             map.put("user",userRes);
         }
         return map;
     }

    @Override
    public User getUserById(Integer id) {
//       try{
           return userMapper.getUser(id);
//       }catch (Exception e){
//           return
//       }
    }

    @Override
    public IPage<UserRoleList2> myPageList(Map map) {
        Integer current= (Integer) map.get("pageNumber");
        Integer size= (Integer) map.get("pageSize");
        Page<User> page = new Page<>(current,size);
        return userMapper.selUserRole(page,map);
    }


}
