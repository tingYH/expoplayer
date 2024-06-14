package com.example.exoplayerdemo.http;


/**
 * 自定义异常，当接口返回的code不为200时，需要跑出此异常
 * eg：登陆时验证码错误；参数为传递等
 */
public class APIException extends RuntimeException {
    private int code;
    private String msg;

    public APIException(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
