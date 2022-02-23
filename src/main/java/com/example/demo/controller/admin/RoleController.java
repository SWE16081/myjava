package com.example.demo.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.exception.BusinessException;
import com.example.demo.groups.update;
import com.example.demo.groups.user.add;
import com.example.demo.service.RoleService;
import com.example.demo.tools.ApiMessage;
import com.example.demo.untils.ResultReturn;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.example.demo.tools.CommonTools.makePage;

@Api("Role控制器")
@RestController
@RequestMapping("/admin")
public class RoleController {
    @Autowired
    RoleService roleService;
    @ApiOperation(value = "角色添加",notes="根据role对象创建角色")
    @ApiImplicitParam(name = "role", value = "角色实体role", required = true, dataType = "Role")
    @PostMapping("/role-add")
    @UserLoginToken
    public ResultReturn add(@RequestBody @Validated(value= add.class) Role role){
            roleService.add(role);
            return ResultReturn.success("添加角色成功");
    }
    @PostMapping("/role-update")
    public ResultReturn update(@RequestBody @Validated(value= update.class)Role role){
            roleService.myUpdate(role);
            return ResultReturn.success("更新角色成功");
    }
    @PostMapping("/role-del")
    public ResultReturn del(@RequestBody @Validated(value = update.class)Role role){
            roleService.myDelete(role.getId());
            return  ResultReturn.success("删除角色成功");
    }
    @ApiOperation(value = "角色查询",notes="角色分页查询")
    @PostMapping("/role-list")
    public ResultReturn list(@RequestBody Map params){
        Map map=makePage(params);
        Page res=roleService.myPageList(map);
        return  ResultReturn.success("查询成功",res);
    }
    @PostMapping("/role-list2")
    public ResultReturn list2(@RequestBody Map<String, Object>  params){
        //
        Map<String, Object> map=makePage(params);
        IPage res=roleService.myPageList2(map);
        return  ResultReturn.success("查询成功",res);
    }
    /**
     * springboot post传参 参数接收的几种方式
     * @return
     */
    @PostMapping("/post-param-test")
    public String test(@RequestParam("name") String name,@RequestParam("age")Integer age){
        return "name:"+name+"age:"+age;
    }
    @PostMapping("/post-param-test2")
    public String test2(@RequestParam(value = "name",defaultValue = "test") String name,
                        @RequestParam(value = "age",required = false)Integer age){
        return "name:"+name+"age:"+age;
    }
    @PostMapping("/post-param-test3")
    public String test3(@RequestParam Map<String,Object> params){
        return "name:"+params.get("name")+"age:"+params.get("age");
    }
    @PostMapping("/post-param-test4")
    public String test4(@RequestParam("name") String[] names){
        String result = "";
        for(String name:names){
            result += name + "\n";
        }
        return result;
    }
    @PostMapping("/post-param-test5")
    public String test5(Role role){
        return role.getRoleName()+"_"+role.getType();
    }
    @PostMapping("/post-param-test6")
    public String test6(HttpServletRequest request){
        ServletInputStream is = null;
        try {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            System.out.println(sb.toString());
            return "获取到的文本内容为：" + sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @PostMapping("/post-param-test7")
    public String test7(@RequestBody Map params){
        return "name：" + params.get("name") + "\n age：" + params.get("age");
    }
}
