package com.example.appjava.exception;


import com.example.appjava.constant.CommonCode;

public class CommonException extends RuntimeException {
    private String errorCode;

    public CommonException(String code, String message) {
        super(message);
        this.errorCode = code;
    }

    public CommonException(CommonCode commonCode) {
        super(commonCode.getMessage());
        this.errorCode = commonCode.getCode();
    }

    public String getErrorCode(){
        return this.errorCode;
    }
}
