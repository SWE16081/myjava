package com.example.demo.tools;

public enum ApiCode {


    SEL_SUCCESS(200,"查询成功"),
    REGISTER_SUCCESS(200,"注册成功"),

    CAPTCHA_ERROR(404,"验证码错误"),
    USERNAME_PWD_ERROE(404,"账号密码错误"),
    DATABASE_SEL_ERROR(404,"数据库查询失败"),
    USER_LOGIN_ERROR(404,"用户登录失败");
    private int code;
    private String msg;

    /**
     *
     * @param code
     * @param msg
     */
    ApiCode(int  code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }


    public String getMessage() {
        return this.msg;
    }
}
