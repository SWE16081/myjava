package com.example.demo.untils;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.time.Instant.now;

public class ResultReturn2{
    private boolean success;
    private Integer code;
    private Object data;
    private Object message;
    private Long currentTime;

    public ResultReturn2(){
        this.currentTime=now().toEpochMilli();
    }

    public ResultReturn2(boolean success, Integer code, Object data, Object message) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.message = message;
        this.currentTime = now().toEpochMilli();
    }

    @Override
    public String toString() {
        return "ResultReturn{" +
                "success=" + success +
                ", code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
    public  ResultReturn2 success(Object data, Object message) {
        this.success = true;
        this.code = 200;
        this.data = data;
        this.message = message;
        return this;
    }
    public  ResultReturn2 success(Object message) {
        this.success = true;
        this.code = 200;
        this.data = null;
        this.message = message;
        return this;
    }
    public ResultReturn2 error(Integer code, Object data, Object message) {
        this.success = false;
        this.code = code;
        this.data = data;
        this.message = message;
        return this;
    }
    public boolean isRestReturnJson(String data) {
        return true;
        //临时实现先判定下字符串的格式和字段
//        try {
//            /**
//             * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
//             */
//            ObjectMapper mapper = new ObjectMapper();
//            ResultReturn dataRestReturn = mapper.readValue(data, ResultReturn.class);
//            //比较两个类的字段,如果一致返回为真，不一致返回为假
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
