package com.example.demo.tools;




import com.example.demo.bean.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUntil {
    private  static Validator validator;
    static{
        //构造默认的校验工厂，并通过getValidator获取validator实例
        validator= Validation.buildDefaultValidatorFactory().getValidator();
    }
    public static String  valid(User user){
        //鼠标光标；之后 alt+enter 添加变量
        //被校验对象没有通过，则set里面有校验信息
        Set<ConstraintViolation<Object>> set =validator.validate(user);
        System.out.println(set);
        return set.iterator().next().getMessage();
        //获取错误信息
//        List<String> collect = set.stream().map(v -> "属性" + v.getPropertyPath() + ",属性的值"
//                + v.getInvalidValue() + ",校验不通过的提示信息" + v.getMessage()).collect(Collectors.toList());
//        List<String> collect = set.stream().map(v.getMessage()).collect(Collectors.toList());
//        return collect;
    }
}
