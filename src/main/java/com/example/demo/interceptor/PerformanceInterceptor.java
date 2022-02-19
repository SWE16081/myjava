package com.example.demo.interceptor;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  性能检测 拦截器
 *  记录运行时间 日志等
 */
public class PerformanceInterceptor implements HandlerInterceptor {
   private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");
   private Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime =System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
        return true;//继续流程
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
           long endTime=System.currentTimeMillis();//2、结束时间
           long beginTime=startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
           long consumeTime=endTime-beginTime;//3、消耗的时间
           if(consumeTime>500) {//此处认为处理时间超过500毫秒的请求为慢请求
               //TODO记录到日志文件
               logger.info(()->String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
               //测试的时候由于请求时间未超过500，所以启用该代码//
               // logger.info(()->String.format("%s consume %d millis",request.getRequestURI(),consumeTime));
           }

    }
}
