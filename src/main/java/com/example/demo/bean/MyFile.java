package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.groups.add;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@NoArgsConstructor//无参构造器
@AllArgsConstructor//有参构造器
@TableName("file")
public class MyFile {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    @NotNull(message = "文件名称不能为空",groups = {add.class})
    private String  name;
    private String suffix;//文件后缀
    @NotNull(message = "文类型不能为空",groups = {add.class})
    private Integer fileType;
    @NotNull(message = "文件类型不能为空",groups={add.class})
    private String type;
    @NotNull(message="文件路径不能为空",groups = {add.class})
    private String url;
    @NotNull(message ="文件父级id不能为空",groups = {add.class})
    private Integer parentId;
//    @NotNull(message ="用户id不能为空",groups = {add.class})
    private Integer userId;
//    @NotNull(message ="空间id不能为空",groups = {add.class})
    private Integer spaceId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
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
