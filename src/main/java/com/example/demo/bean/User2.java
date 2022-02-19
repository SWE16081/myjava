package com.example.demo.bean;


import lombok.Data;

import java.sql.Time;

@Data

public class User2 {



    private Integer id;

    private  String name;

    private  String phone;
    private  String headImage;

    private String password;


    private Time createdAt;

    private Time updatedAt;


}
