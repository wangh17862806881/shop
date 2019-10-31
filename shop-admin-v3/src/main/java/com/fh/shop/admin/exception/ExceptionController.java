package com.fh.shop.admin.exception;

import com.fh.shop.admin.common.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController{
    @ExceptionHandler(Exception.class)
    @ResponseBody
   public ServerResponse exceptionHandler(Exception ex){
        //控制台打印
       ex.printStackTrace();
       return ServerResponse.error();
   }
}
