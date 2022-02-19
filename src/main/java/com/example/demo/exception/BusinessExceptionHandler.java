package com.example.demo.exception;


import com.example.demo.tools.ApiMessage;
import com.example.demo.untils.ResultReturn;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;



/**
 * 自定义异常处理类
 * @ControllerAdvice
 * 如果是返回json数据 则用 @RestControllerAdvice,就可以不加 @ResponseBody
 */
@RestControllerAdvice
public class BusinessExceptionHandler implements ResponseBodyAdvice<Object> {
//    @ExceptionHandler(BusinessException.class)
////    @RequestBody
//    public ResultReturn handler(BusinessException e){
//        return ResultReturn.fail(e.getCode(),e.getMessage());
//    }

    /**
     * 判定哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
     * */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        //获取当前处理请求的controller的方法
        //String methodName = methodParameter.getMethod().getName();
        // 拦/不拦截处理返回值的方法，如登录
        //String method = "login";
        //这里可以加入很多判定，如果在白名单的List里面，是否拦截
//        return true;
        //contains("springfox")包含springfox返回false
        return !methodParameter.getDeclaringClass().getName().contains("springfox");
    }

    /**
     * 返回前对body，request，response等请求做处理
     * @param body
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
//        //具体返回值处理
        //情况1 如果返回的body为null
        if(body == null){
            if (mediaType == MediaType.APPLICATION_JSON) {
                //返回是json个格式类型，无body内容
                return ResultReturn.success("", "");
            } else {
                return null;
            }
        } else {
            //情况2 文件上传下载，不需要改动，直接返回
            if (body instanceof Resource) {
                return body;
            } else if (body instanceof String) {
                    ObjectMapper om = new ObjectMapper();
                    return om.writeValueAsString(ResultReturn.success("",body));
            } else {
                //返回的是非字符串格式，实际上很多时候用都是是在应用程返回的对象居多
                if(body instanceof ResultReturn){
                    //情况5 如果已经封装成ResultReturn,直接return
                    return body;
                }else{
                    //情况6 非字符串非统一格式的返回，需要统一格式
                    //需要判定是否是抛出的异常返回（统一到错误输出）
                    return ResultReturn.success( "",body);
                }
            }
        }
    }

}
