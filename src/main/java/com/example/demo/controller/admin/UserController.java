package com.example.demo.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.bean.UserRole;
import com.example.demo.exception.BusinessException;
import com.example.demo.groups.user.add;
import com.example.demo.groups.user.login;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.tools.ApiCode;
import com.example.demo.tools.ApiMessage;
import com.example.demo.untils.ResultReturn;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.example.demo.tools.CommonTools.makePage;

@RestController
@RequestMapping("/admin")
public class UserController {
    private static Validator validator;
    /**
     * 后台用户注册
     * @return
     */
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserMapper userMapper;
    @PostMapping("/add")
    /**
     * 为何@Validated()返回的参数验证错误信息 或被businessException捕捉到
     */
    public ResultReturn add(@RequestBody @Validated(value= add.class)   User user){
            userService.add(user);
            return ResultReturn.success("添加用户成功");
    }

    /**
     *
     * 登录接口
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResultReturn login(@RequestBody @Validated(value=login.class)   User user){
           Object map= userService.login(user);
            return ResultReturn.success("登录成功",map);
    }

    @PostMapping("/register")
    public ApiMessage register(HttpServletRequest request, @RequestBody @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            String message=bindingResult.getFieldError().getDefaultMessage();
            return ApiMessage.fail(message);
        }
//        String name=request.getParameter("name");
//        String password=request.getParameter("password");
//        String name=user.getName();
//        String password=user.getPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.eq("name",user.getName());
//        User res = userService.getOne(queryWrapper);
//        userDao.add(user);
        return ApiMessage.success(ApiCode.REGISTER_SUCCESS);
    }

//    @UserLoginToken
    @GetMapping("/list")
    public ApiMessage getList(){
        User user=userService.getUserById(6);
//        User user=userMapper.getUser(6);
        return ApiMessage.success("查询用户列表成功",user);
    }
    @PostMapping("/user/add-role")
    public ApiMessage userAddRole(@RequestBody @Valid UserRole userRole,BindingResult bindingResult){
        try{
            //参数验证
            if(bindingResult.hasErrors()){
                String message=bindingResult.getFieldError().getDefaultMessage();
                return ApiMessage.fail(message);
            }
            userRoleService.add(userRole);
            return ApiMessage.success("用户添加角色成功");
        }catch (BusinessException e){
            return ApiMessage.fail("用户添加角色失败");
        }
    }
    @PostMapping("/user/update-role")
    public ApiMessage userUpdate(@RequestBody @Valid UserRole userRole,BindingResult bindingResult){
        try{
            //参数验证
            if(bindingResult.hasErrors()){
                String message=bindingResult.getFieldError().getDefaultMessage();
                return ApiMessage.fail(message);
            }
            userRoleService.myUpdate(userRole);
            return ApiMessage.success("用户更新角色成功");
        }catch (BusinessException e){
            return ApiMessage.fail("用户更新角色失败");
        }
    }
    @PostMapping("/user/role-list")
    public ApiMessage userRoleList(@RequestBody Map params, User user){
        Map map=makePage(params);
        IPage res=userService.myPageList(map);
        return ApiMessage.success("查询成功",res);
    }
}
