package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.groups.user.add;
import com.example.demo.groups.user.login;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

//@Data
@NoArgsConstructor//无参构造器
@AllArgsConstructor//有参构造器
@TableName("users")
public class User {
    @TableId(value="id",type=IdType.AUTO)
    private Integer id;
    @NotNull(message = "用户帐号不能为空",groups = {add.class,login.class})
    private  String name;
    @NotNull(message = "用户手机号不能为空",groups = {add.class})
    @Pattern(regexp = "^[1][3456789][0-9]{9}$",message="手机号格式错误",groups = {add.class})
    private  String phone;
    private  String headImage;
    private Integer deleted;

    @NotNull(message = "用户密码不能为空",groups = {add.class,login.class})
    @Size(min=6,max=16,message = "用户密码长度要在6-16字符之间",groups = add.class)
    private String password;
    @TableField(fill = FieldFill.INSERT,jdbcType = JdbcType.TIMESTAMP)
    private Timestamp createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE,jdbcType = JdbcType.TIMESTAMP)
    private Timestamp  updatedAt;
    public User(String name,String password){
        this.name=name;
        this.password=password;
    }
    public Integer getId() {
        return id;
    }

    public Integer getDeleted() {

        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
