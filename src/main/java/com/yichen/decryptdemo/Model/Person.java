package com.yichen.decryptdemo.Model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/1 22:30
 * @describe
 */
@Data
public class Person {

    private String name;
    private String address;
    @NotNull(message = "年龄不能为null")
    private Integer age;

    /**
     * 加密传输数据的字段
     */
    private String encryptData;

}
