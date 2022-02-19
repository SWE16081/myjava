package com.example.demo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.annotation.PassToken;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.bean.User;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * token验证拦截器
 * 继承HandlerInterceptor拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;


    /**
     * token 预处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new BusinessException(401,"api未携带token,请重新登录");
                }
                // 获取 token 中的 user id
                String userId;
                DecodedJWT jwt = JWT.decode(token);
                try {
                    //获取token中用户id
                    userId = jwt.getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new BusinessException(401,"从token获取用户信息失败");
                }

                User user=userService.getUserById(Integer.valueOf(userId));
                if (user == null) {
                    throw new BusinessException(401,"用户不存在，请重新登录");
                }
                // 验证 token 正确性 是否过期
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                }catch (TokenExpiredException e){
                    throw new BusinessException(401,"token过期,请重新登录");
                } catch (JWTVerificationException e) {
                    throw new BusinessException(401,"非法token,请重新登录");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
