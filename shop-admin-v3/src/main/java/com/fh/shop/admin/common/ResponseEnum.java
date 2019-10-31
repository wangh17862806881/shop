package com.fh.shop.admin.common;

public enum ResponseEnum {
    PASSWORD_IS_ERROR(1002,"密码错误"),

    USERNAME_IS_ERROR(1001,"用户名错误"),

    USERNAME_PASSWORD_IS_EMPTY(1000,"用户名或者密码或者验证码为空"),

    USER_IS_LOCK(1003,"当前用户被锁定"),

    PASSWORD_IS_EMPTY(1004,"密码不能为空"),

    PASSWORD_IS_DIFFERENT(1005,"两次新密码不一致"),

    OLDPASSWORD_IS_ERROR(1006,"原密码错误"),

    USER_IS_EMPTY(1007,"用户不存在"),

    USERNAME_EMAIL_IS_EMPTY(1008,"用户名或邮箱为空"),

    USER_IS_NOT_LOGIN(1009,"请验证后在找回密码"),

    EMAIL_is_defferent(1010,"邮箱和注册时邮箱不同请确认"),

    POST_IS_ERROR(1011,"不正当请求"),

    IMGCODE_IS_ERROR(1012,"验证码错误"),
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
