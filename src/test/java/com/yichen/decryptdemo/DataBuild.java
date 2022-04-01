package com.yichen.decryptdemo;

import com.alibaba.fastjson.JSON;
import com.yichen.decryptdemo.Model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/1 22:37
 * @describe
 */
@SpringBootTest
public class DataBuild {

    /**
     *  加密后的数据    eyJhZGRyZXNzIjoi5rW35bqV5Lik5LiH6YeMIiwiYWdlIjoxOCwibmFtZSI6IuWlleaZqCJ9   => 三个字段都有
     *   eyJhZGRyZXNzIjoi5rW35bqV5Lik5LiH6YeMIiwibmFtZSI6IuWlleaZqCJ9   => age 无   => 可以进行比对
     */
    @Test
    public void buildData() throws Exception{
        Person person = new Person();
        person.setAddress("海底两万里");
//        person.setAge(18);
        person.setName("奕晨");
        System.out.println(new String(Base64.getEncoder().encode(JSON.toJSONString(person).getBytes(StandardCharsets.UTF_8)),"UTF-8"));
    }

    @Test
    public void decrypt() throws Exception{
        String s = "eyJhZGRyZXNzIjoi5rW35bqV5Lik5LiH6YeMIiwiYWdlIjoxOCwibmFtZSI6IuWlleaZqCJ9";
        String decryptString = new String(Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8)),"UTF-8");
        System.out.println(decryptString);
    }

}
