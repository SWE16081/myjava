package com.example.demo.bean;

import com.example.demo.annotation.PrimaryKey;
import lombok.Data;

@Data
//@NoArgsConstructor//无参构造器
////@AllArgsConstructor//有参构造器
//@TableName("city")
public class City {
    @PrimaryKey
    private Integer id;
    private String name;
    private String state;
    private String country;
}
