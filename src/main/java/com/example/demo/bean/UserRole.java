package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.groups.add;
import com.example.demo.groups.update;

import javax.validation.constraints.NotNull;
import java.sql.Time;

@TableName("user_role")
public class UserRole {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    @NotNull(message = "角色id不能为空",groups = {add.class, update.class})
    private String roleId;
    @NotNull(message = "用户id不能为空",groups = {add.class, update.class})
    private String userId;
    private Integer deleted;
    @TableField(fill= FieldFill.INSERT)
    private Time createdAt;
    @TableField(fill =FieldFill.UPDATE)
    private Time uodatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
        this.createdAt = createdAt;
    }

    public Time getUodatedAt() {
        return uodatedAt;
    }

    public void setUodatedAt(Time uodatedAt) {
        this.uodatedAt = uodatedAt;
    }
}
