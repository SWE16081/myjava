package com.example.demo.exception;

/**
 * 自定义业务异常返回信息枚举类
 */
public enum ExceptionCode {
    HTTP_OK(200,"请求成功"),
    HTTP_ERROR(400,"请求失败"),
    ;

    ExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
