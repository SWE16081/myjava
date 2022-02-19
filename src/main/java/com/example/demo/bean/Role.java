package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.groups.add;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;

@NoArgsConstructor//无参构造器
@AllArgsConstructor//有参构造器
@TableName("role")
public class Role {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    @NotNull(message = "角色名称不能为空",groups = {add.class})
    private String  roleName;
    @NotNull(message = "角色类型不能为空",groups={add.class})
    private String type;
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT,jdbcType = JdbcType.TIMESTAMP)
    private Timestamp createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE,jdbcType = JdbcType.TIMESTAMP)
    private Timestamp  updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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
