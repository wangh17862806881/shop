package com.fh.shop.api.util;

public final class SystemConst {

    public final static String CURRENT_USER = "user";

    public static final String USER_RESOURCE_MENU = "userResource";

    public static final String RESOURCE_ALL_LIST = "userResourceAllList";

    public static final String USER_RESOURCE_ALL_URL_LIST = "userResource_Url_List";

    public static final Integer LOGGER_SUCCESS_STATUS = 1;

    public static final Integer LOGGER_ERROR_STATUS = 0;

    public static final Integer LOGIN_ERROR_COUNT = 3;

    public static final String RESET_DEFAULT_PASSWORD = "123456";

    public static final Integer USER_IS_EXIST =1;

    public static final Integer POST_EMAIL =2;

    public static final String USER_FORGET_PASSWORD = "forgetPassword";

    public static final String VERIFY_NAME ="verifycode";

    public static final int VERIFY_EXPIRE =  10*60;

    public static final int SMS_CODE_EXPIRE =  10*60;

    public static final String SECRET = "!@#$%^&*())_+:?>)(*&^%$#@!<{}|*-+";

    public static final int MEMBER_EXPIRE =  30*60;


    public static final String CURRENT_MEMBERNAME = "memberInfo";

    public static final String REDIS_CART_MAP_NAME = "memberMap";

    public static final String ALL_PRODUCT_STOCK_NAME = "stockCount";

    public static final String WXPAY_IS_ERROR = "微信支付平台错误";

    public static final String POSTQRCODE_ERROR = "发送失败,二维码过期";


}
