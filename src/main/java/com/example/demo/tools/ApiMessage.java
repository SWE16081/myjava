package com.example.demo.tools;

public class ApiMessage {
    private static final String STATUS_SUCCESS="success";
    private static final String STATUS_FAIL="fail";
    private String status;
    private int code;
    private String message;
    private Object data;
    public ApiMessage(String status, int code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ApiMessage(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public  static ApiMessage success(ApiCode apiCode){

        return new ApiMessage(STATUS_SUCCESS,apiCode.getCode(),apiCode.getMessage());
    }
    public static ApiMessage success( ApiCode apiCode, Object data){
        return new ApiMessage(STATUS_SUCCESS,apiCode.getCode(),  apiCode.getMessage(),  data);
    }
    public static ApiMessage success( String message, Object data) {
        return new ApiMessage(STATUS_SUCCESS, 200, message,data);
    }
    public static ApiMessage success( String message) {
        return new ApiMessage(STATUS_SUCCESS, 200, message);
    }
        public  static ApiMessage fail(ApiCode apiCode){
        return new ApiMessage(STATUS_FAIL,apiCode.getCode(),apiCode.getMessage());
    }
    public static ApiMessage fail(int code, String message){
        return new ApiMessage(STATUS_FAIL,code,message);
    }
    public  static ApiMessage fail(String message){

        return new ApiMessage(STATUS_FAIL,400,message);
    }
    public static ApiMessage fail( ApiCode apiCode, Object data){
        return new ApiMessage(STATUS_FAIL,apiCode.getCode(),  apiCode.getMessage(),  data);
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
