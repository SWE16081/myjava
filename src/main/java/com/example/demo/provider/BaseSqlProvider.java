package com.example.demo.provider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.annotation.Exclude;
import com.example.demo.annotation.PrimaryKey;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.jdbc.SQL;
import com.example.demo.tools.DatabaseTool;
import org.assertj.core.error.ShouldBeAfterYear;



public class BaseSqlProvider<T> {

    @Options
    /**
     * 使用SQL构造器来执行sql语句
     */
    public String add(T bean) {
        SQL sql = new SQL();
        Class clazz = bean.getClass();
        String tableName = clazz.getSimpleName();
        //提取实体名称
//        String realTableName = DatabaseTool.lineToHump(tableName).replaceAll("_entity", "").substring(1);
        sql.INSERT_INTO(tableName);
        //getFields()获取实体类字段
        List<Field> fields = getFields(clazz);
        for (Field field : fields) {
            field.setAccessible(true);
            String column = field.getName();
            System.out.println("column:" + DatabaseTool.lineToHump(column));
            //values()插入到insert语句中。第一个参数是要插入的列名，第二个参数则是该列的值
            sql.VALUES(DatabaseTool.lineToHump(column), String.format("#{" + column + ",jdbcType=VARCHAR}"));
        }

        return sql.toString();
    }

    public String delete(T bean) {

        SQL sql = new SQL();

        Class clazz = bean.getClass();

        String tableName = clazz.getSimpleName();

//        String realTableName = DatabaseTool.lineToHump(tableName).replaceAll("_entity", "").substring(1);
        sql.DELETE_FROM(tableName);

        List<Field> primaryKeyField = getPrimarkKeyFields(clazz);

        if (!primaryKeyField.isEmpty()) {

            for (Field pkField : primaryKeyField) {
                pkField.setAccessible(true);
                sql.WHERE(pkField.getName() + "=" + String.format("#{" + pkField.getName() + "}"));
            }

        } else {

            sql.WHERE(" 1= 2");

            throw new RuntimeException("对象中未包含PrimaryKey属性");
        }

        return sql.toString();
    }

    //获取主键字段
    private List<Field> getPrimarkKeyFields(Class clazz) {
        List<Field> primaryKeyField = new ArrayList<>();
        List<Field> fields = getFields(clazz);
        for (Field field : fields) {
            field.setAccessible(true);
            //getAnnotation()返回此元素上存在的所有注解。如果此元素没有注解，则返回长度为零的数组
            PrimaryKey key =  field.getAnnotation(PrimaryKey.class);
            if (key != null) {
                primaryKeyField.add(field);
            }
        }
        return primaryKeyField;
    }

    /**
     * 获取实体类字段
     * @param clazz
     * @return
     */
    private List<Field> getFields(Class clazz) {
        List<Field> fieldList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //允许我们对类的私有字段进行操作
            field.setAccessible(true);
            Exclude key = field.getAnnotation(Exclude.class);
            System.out.println("key:"+key);
            if (key == null) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }

    public String get(T bean) {

        SQL sql = new SQL();

        Class clazz = bean.getClass();

        String tableName = clazz.getSimpleName();

        String realTableName = DatabaseTool.lineToHump(tableName).replaceAll("_entity", "").substring(1);
        sql.SELECT("*").FROM(realTableName);

        List<Field> primaryKeyField = getPrimarkKeyFields(clazz);

        if (!primaryKeyField.isEmpty()) {

            for (Field pkField : primaryKeyField) {
                pkField.setAccessible(true);
                sql.WHERE(pkField.getName() + "=" + String.format("#{" + pkField.getName() + "}"));

            }
        } else {

            sql.WHERE(" 1= 2");

            throw new RuntimeException("对象中未包含PrimaryKey属性");
        }
        System.out.println("getSql:"+sql.toString());
        return sql.toString();
    }

    public String update(T bean) {

        SQL sql = new SQL();

        Class clazz = bean.getClass();

        String tableName = clazz.getSimpleName();

//        String realTableName = DatabaseTool.lineToHump(tableName).replaceAll("_entity", "").substring(1);
        sql.UPDATE(tableName);

        List<Field> fields = getFields(clazz);
        for (Field field : fields) {

            field.setAccessible(true);

            String column = field.getName();

            if (column.equals("id")) {
                continue;
            }

            System.out.println(DatabaseTool.lineToHump(column));

            sql.SET(DatabaseTool.lineToHump(column) + "=" + String.format("#{" + column + ",jdbcType=VARCHAR}"));
        }

        List<Field> primaryKeyField = getPrimarkKeyFields(clazz);
        System.out.println("primaryKeyField:"+primaryKeyField);
        if (!primaryKeyField.isEmpty()) {

            for (Field pkField : primaryKeyField) {
                System.out.println("pkField:"+pkField);
                pkField.setAccessible(true);
                sql.WHERE(pkField.getName() + "=" + String.format("#{" + pkField.getName() + "}"));
            }

        } else {

            sql.WHERE(" 1= 2");

            throw new RuntimeException("对象中未包含PrimaryKey属性");
        }
        System.out.println("updateSql:"+sql.toString());
        return sql.toString();

    }

}
