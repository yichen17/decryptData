package com.yichen.decryptdemo.controller;

import com.alibaba.fastjson.JSON;
import com.yichen.decryptdemo.Model.Person;
import com.yichen.decryptdemo.annotation.Decrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/1 22:25
 * @describe 解密测试 controller
 */
@Slf4j
@RestController
public class DecryptController {

    /**
     * 测试      http://localhost:8080/annotation
     *     全参
     *     {
     *     "encryptData":"eyJhZGRyZXNzIjoi5rW35bqV5Lik5LiH6YeMIiwiYWdlIjoxOCwibmFtZSI6IuWlleaZqCJ9"
     *     }
     *
     *     缺 age 字段
     *     {
     *     "encryptData":"eyJhZGRyZXNzIjoi5rW35bqV5Lik5LiH6YeMIiwibmFtZSI6IuWlleaZqCJ9"
     *     }
     */
    @RequestMapping("/annotation")
    @Decrypt
    public String annotationDecrypt(@RequestBody @Valid Person person){
        log.info("通过注解解密数据");
        return JSON.toJSONString(person);
    }


    @RequestMapping("/filter")
    public String filterDecrypt(@RequestBody @Valid Person person){
        log.info("通过过滤器和拦截器解密数据");
        return JSON.toJSONString(person);
    }

}
