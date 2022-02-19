package com.example.demo.tools;

import org.springframework.util.DigestUtils;

public class MyMd5Tool {

    public static String getMd5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
