package com.wenshuo.serviceBase;

import com.wenshuo.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public R GlobalExceptionHandler(Exception exception){
        exception.printStackTrace();
        return R.error().message(exception.getMessage());
    }
}
