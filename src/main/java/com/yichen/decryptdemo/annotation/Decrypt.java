package com.yichen.decryptdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/1 22:32
 * @describe 数据解密注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Decrypt {
}
