package com.fh.shop.admin.common;

import java.io.Serializable;

public class ServerResponse implements Serializable {

    //状态码
    private int code;
    //信息
    private String msg;
    //数据
    private Object data;

//静态方法

// 无返回值有成功方法 调用    方法重载
    public static ServerResponse success(){
        return new ServerResponse(200,"ok",null);
    }
// 有返回值成功方法  调用   方法重载
    public static ServerResponse success(Object data){
        return new ServerResponse(200,"ok",data);
    }

// 失败调用方法  静态
    public  static ServerResponse error(){
        return new ServerResponse(-1,"error",null);
    }
// 失败调用方法 有返回值  方法重载  静态   使用枚举 做参数
    public  static ServerResponse error(ResponseEnum responseEnum){
        return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),null);
    }

    public int getCode() {
        return code;

    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
//私有方法  防止创建对象   只允许本类使用
    private ServerResponse() {
    }

//私有方法  防止创建对象   只允许本类使用
    private ServerResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
