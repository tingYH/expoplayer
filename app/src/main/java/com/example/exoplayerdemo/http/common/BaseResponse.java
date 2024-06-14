package com.example.exoplayerdemo.http.common;

import java.io.Serializable;


public class BaseResponse<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    private String token;
    public static int TOKEN_PAYMENT_ERROR_CODE = 6;

    public boolean isSuccess() {
        return code == 0;
    }


    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }
}
