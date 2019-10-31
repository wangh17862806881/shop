package com.fh.shop.api.exception;

import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HeaderGlobleException{

    @ExceptionHandler(HeaderExceptionInfo.class)
    public ServerResponse throwHeaderGlobleException(HeaderExceptionInfo headerExceptionInfo){

        ResponseEnum enumInfo = headerExceptionInfo.getEnumInfo();

        return  ServerResponse.error(enumInfo);
    }




}
