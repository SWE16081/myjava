//package com.example.demo.controller;
//
//import club.herry.demo.springboottest.bean.Student;
//import club.herry.demo.springboottest.bean.User;
//import club.herry.demo.springboottest.service.UserService;
//import club.herry.demo.springboottest.tools.ApiCode;
//import club.herry.demo.springboottest.tools.ApiMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@RestController
//public class UserTestController {
//
//    @Autowired
//    UserService userService;
//    @PostMapping("/test")
//    public ApiMessage test(){
//        return ApiMessage.success(ApiCode.SEL_SUCCESS);
//    }
//    @GetMapping("/user")
//    public List getUser(@RequestParam("id") Long id){
//      List <User> list=  userService.list();
//      return  list;
//    }
//
//    /**
//     *  api 返回json数据测试
//     * @return
//     */
//    @PostMapping("/test2")
//    public Student test2(){
//        Student student=new Student("李白",21);
//        return student;
//    }
//    @PostMapping("/list")
//    public List<Student> getUserList() {
//        List<Student> studentList = new ArrayList<>();
//        Student student=new Student("李白",21);
//        Student student2=new Student("李白",21);
//        studentList.add(student);
//        studentList.add(student2);
//        return studentList;
//    }
//
//    @PostMapping("/map")
//    public Map<String, Object> getMap() {
//        Map<String, Object> map = new HashMap<>(3);
//        Student student=new Student("李白",21);
//        map.put("作者信息", student);
//        map.put("博客地址", "http://blog.itcodai.com");
//        map.put("CSDN地址", "http://blog.csdn.net/eson_15");
//        map.put("粉丝数量", 4153);
//        return map;
//    }
//
//}
