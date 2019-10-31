package com.fh.shop.api.common;

public enum ResponseEnum {
    PASSWORD_IS_ERROR(1002,"密码错误"),

    USERNAME_IS_ERROR(1001,"用户名错误"),

    USERNAME_PASSWORD_IS_EMPTY(1000,"用户名或者密码为空"),

    USER_IS_LOCK(1003,"当前用户被锁定"),

    PASSWORD_IS_EMPTY(1004,"密码不能为空"),

    PASSWORD_IS_DIFFERENT(1005,"两次新密码不一致"),

    OLDPASSWORD_IS_ERROR(1006,"原密码错误"),

    USER_IS_EMPTY(1007,"用户不存在"),

    USERNAME_EMAIL_IS_EMPTY(1008,"用户名或邮箱为空"),

    USER_IS_NOT_LOGIN(1009,"请验证后在找回密码"),

    EMAIL_is_defferent(1011,"邮箱和注册时邮箱不同请确认"),

    POST_IS_ERROR(1010,"不正当请求"),

    VERIFY_IS_ERROR(1012,"验证码错误"),

    PHONE_IS_NULL(1013,"手机号为空"),

    PHONE_IS_EXIST(1013,"手机号已被注册"),

    SMSCODE_IS_ERROR(1014,"验证码发送失败"),

    USERNAME_IS_NULL(1015,"用户名不能为空"),

    USERNAME_IS_EXIST(1016,"用户名已存在"),

    EMAIL_IS_NULL(1017,"邮箱不能为空"),

    EMAIL_IS_EXIST(1018,"邮箱已被注册"),

    SMS_IS_NULL(1019,"验证码不能为空"),

    SMS_IS_EXPIRE(1020,"验证码已过期"),

    PHONE_IS_ERROR(1021,"手机号码格式错误"),

    HEADER_IS_NULL(2000,"头部信息缺失"),

    HEADER_IS_UPDATE(2001,"头部信息被修改"),

    HEADER_IS_ERROR(2002,"头部信息不正确"),

    LOGIN_TIMEOUT(2003,"登陆超时"),

    PRODUCT_IS_NULL(3000,"商品不存在"),

    PRODUCT_IS_NOT_GROUND(3001,"商品未上架"),

    CART_IS_NULL(3002,"购物车是空的"),

    MEMBER_IS_NOT_REGISTER(3003,"请注册后在进行免密登陆"),

    REQUEST_IS_REPLACE(3004,"请勿重复提交"),

    ORDER_NOT_COMMIT(3005,"订单中商品都不能下单，库存不足"),

    ORDER_IS_NOT_EXIST(3006,"订单不存在"),

    ;


    private int code;

    private String msg;

    private ResponseEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
