package com.fh.shop.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//注解作用在方法上
@Target(ElementType.METHOD)
//运行时生效
@Retention(RetentionPolicy.RUNTIME)
//空注解 当成标志位使用
public @interface check {

}
