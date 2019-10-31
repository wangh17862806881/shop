package com.fh.shop.api.exception;

import com.fh.shop.api.common.ResponseEnum;

public class HeaderExceptionInfo  extends RuntimeException {

    private ResponseEnum enumInfo;


    public HeaderExceptionInfo(ResponseEnum enumInfo){
        this.enumInfo = enumInfo;
    }

    public ResponseEnum getEnumInfo () {
        return enumInfo;

    }


}
