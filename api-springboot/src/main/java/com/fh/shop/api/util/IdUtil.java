package com.fh.shop.api.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdUtil {
    //雪花算法生成id  31位
    public static String createId(){
        DateTimeFormatter yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String format = LocalDateTime.now().format(yyyyMMddHHmm)+IdWorker.getIdStr();
        return format;
    }


}
