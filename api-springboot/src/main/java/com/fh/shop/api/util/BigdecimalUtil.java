package com.fh.shop.api.util;

import java.math.BigDecimal;

public class BigdecimalUtil {

    //bigdemal 相乘
    public static BigDecimal multiply(String b1,String b2){
        BigDecimal bigDecimal = new BigDecimal(b1);
        BigDecimal bigDecimal2 = new BigDecimal(b2);
        return bigDecimal.multiply(bigDecimal2).setScale(2);
    }







}
