package com.example.appjava.exception;

public class ValidateException extends Exception {
    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public Throwable fillInStackTrace() {
        return this;
    }
}
