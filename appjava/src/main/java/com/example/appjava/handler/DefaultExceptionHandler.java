package com.example.appjava.handler;

import com.example.appjava.constant.CommonCode;
import com.example.appjava.exception.CommonException;
import com.example.appjava.exception.ValidateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import responseVo.ResponseVo;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler({CommonException.class, ValidateException.class})
    @ResponseBody
    public ResponseVo exceptionHandler(NativeWebRequest request, Exception e) {
        ResponseVo responseVo = new ResponseVo();
        if (e instanceof ValidateException) {
            responseVo.setMessage(e.getMessage());
            responseVo.setCode(CommonCode.PARAMERROR.getCode());
        } else if (e instanceof CommonException) {
            responseVo.setMessage(e.getMessage());
            responseVo.setCode(((CommonException) e).getErrorCode());
        } else {
            responseVo.setCode(CommonCode.UNKNOWERROR.getCode());
            responseVo.setMessage(CommonCode.UNKNOWERROR.getMessage());
        }
        return responseVo;
    }
}
