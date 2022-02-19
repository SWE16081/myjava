package com.example.demo.exception;

/**
 * 自定义业务异常类
 */
public class BusinessException extends RuntimeException{
    private Integer code;

    /**
     * 使用已有的错误类型
     * @param exceptionCode 枚举中的错误类型
     */
    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMsg());
        this.code = exceptionCode.getCode();
    }

    /**
     * 自定义错误类型
     * @param message 自定义错误信息
     * @param code  自定义错误码
     */
    public BusinessException( Integer code,String message) {
        super(message);
        this.code = code;
    }
    public BusinessException(String message) {
        super(message);
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
