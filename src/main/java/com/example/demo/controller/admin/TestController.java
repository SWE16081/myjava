package com.example.demo.controller.admin;

import com.example.demo.untils.ResultReturn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {
    @GetMapping("/test")
    public  ResultReturn test(){
        //其他操作...
//        MultipartFile

        return  ResultReturn.success("查询成功123");
    }

    @GetMapping("/test2")
    public  String test2(){
        return "1231";
    }
}
