package com.example.appjava.constant;

public enum CommonCode {
    SUCCESS("000000", "success"),
    PARAMERROR("000001", "参数有误"),
    UNKNOWERROR("000002", "未知错误"),
    INABLEUSER("000003", "用户名或密码错误"),
    INABLETOKEN("000004", "token失效");
    private String code;
    private String message;

    CommonCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
