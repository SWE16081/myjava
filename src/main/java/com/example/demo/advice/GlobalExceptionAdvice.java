package com.example.demo.advice;


import com.example.demo.exception.BusinessException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.untils.ResultReturn;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionAdvice {
    //拦截业务异常
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public  ResultReturn apiValidateException(BusinessException e){
        // 打印堆栈信息
        e.printStackTrace();
        return new ResultReturn(
                "fail",
                400,
                "",
                e.getMessage()
        );
    }
    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultReturn defaultException(HttpServletRequest request, Exception e){
        //输出堆栈信息到控制台，以后记录到日志
        e.printStackTrace();
        return new ResultReturn(
                "fail",
                RestReturnEnum.EXCEPTION.getCode(),
                "",
                RestReturnEnum.EXCEPTION.getMessage()+"_"+e.getMessage()+"_"+e.getStackTrace()
        );
    }
    /**
     * 处理 接口无权访问异常AccessDeniedException FORBIDDEN(403, "Forbidden"),
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResultReturn accessDeniedException(AccessDeniedException e){
        //输出堆栈信息到控制台，以后记录到日志
        e.printStackTrace();
        return new ResultReturn(
                "fail",
                RestReturnEnum.FORBIDDEN.getCode(),
                "",
                RestReturnEnum.FORBIDDEN.getMessage()
        );
    }

    /**
     * 处理bad请求异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResultReturn badRequestException(RuntimeException e) {
        e.printStackTrace();
        return new ResultReturn(
                "fail",
                RestReturnEnum.BAD_REQUEST.getCode(),
                "",
                RestReturnEnum.BAD_REQUEST.getMessage()
        );
    }
    /**
     * 处理 EntityNotFound 数据库数据未找到
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseBody
    public ResultReturn entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        e.printStackTrace();
        return new ResultReturn(
                "fail",
                RestReturnEnum.NOT_FOUND.getCode(),
                "",
                RestReturnEnum.NOT_FOUND.getMessage()
        );
    }



}
