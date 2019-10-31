package com.fh.shop.api.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil implements Serializable {
    //时间格式   年月日
    public static final String Y_M_D = "yyyy-MM-dd";
    //时间格式   年月日时分秒
    public static final String full_year = "yyyy-MM-dd HH:mm:ss";

    //时间格式  年月日时分秒
    public static final String yyyyMMddHHmmss ="yyyyMMddHHmmss";
    //定义方法
    public static String date2str (Date date,String patton){
        //判断非空
        if(date == null ){
            return "";
        }
        SimpleDateFormat sim = new SimpleDateFormat(patton);
        String format = sim.format(date);
        return format;
    }




}
