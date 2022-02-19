package com.example.demo.untils;

import com.example.demo.tools.ApiCode;
import com.example.demo.tools.ApiMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.time.Instant.now;

public class ResultReturn{
    private static final String STATUS_SUCCESS="success";
    private static final String STATUS_FAIL="fail";
    private String status;
    private Integer code;
    private Object data;
    private Object message;
    private Long currentTime;

    public ResultReturn(){
        this.currentTime=now().toEpochMilli();
    }


    public ResultReturn(String status, int code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
        this.currentTime = now().toEpochMilli();
    }
    public ResultReturn(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.currentTime = now().toEpochMilli();
    }
    public  static ResultReturn success(ApiCode apiCode){

        return new ResultReturn(STATUS_SUCCESS,apiCode.getCode(),apiCode.getMessage());
    }
    public static ResultReturn success( ApiCode apiCode, Object data){
        return new ResultReturn(STATUS_SUCCESS,apiCode.getCode(),  apiCode.getMessage(),  data);
    }
    public static ResultReturn success( String message, Object data) {
        return new ResultReturn(STATUS_SUCCESS, 200, message,data);
    }
    public static ResultReturn success( String message) {
        return new ResultReturn(STATUS_SUCCESS, 200, message);
    }
    public  static ResultReturn fail(ApiCode apiCode){
        return new ResultReturn(STATUS_FAIL,apiCode.getCode(),apiCode.getMessage());
    }
    public static ResultReturn fail(int code, String message){
        return new ResultReturn(STATUS_FAIL,code,message);
    }
    public static ResultReturn fail(int code, String message,Object data){
        return new ResultReturn(STATUS_FAIL,code,message,data);
    }
    public  static ResultReturn fail(String message){

        return new ResultReturn(STATUS_FAIL,400,message);
    }
    public static ResultReturn fail( ApiCode apiCode, Object data){
        return new ResultReturn(STATUS_FAIL,apiCode.getCode(),  apiCode.getMessage(),  data);
    }

    public static boolean isRestReturnJson(String data) {

        //临时实现先判定下字符串的格式和字段
        try {
            /**
             * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
             */
            ObjectMapper mapper = new ObjectMapper();
            ResultReturn dataRestReturn = mapper.readValue(data, ResultReturn.class);//json 字符串赋值给一个类
            //比较两个类的字段,如果一致返回为真，不一致返回为假
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }
}
