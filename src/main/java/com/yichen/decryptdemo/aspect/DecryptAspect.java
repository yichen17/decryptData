package com.yichen.decryptdemo.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yichen.decryptdemo.Model.Person;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/1 22:34
 * @describe 解密切面
 */
@Aspect
@Slf4j
//@Component
public class DecryptAspect {

    @Pointcut("@annotation(com.yichen.decryptdemo.annotation.Decrypt)")
    public void decryptPointCut() {
    }

    @Before("decryptPointCut()")
    public void decrypt(JoinPoint joinPoint){
        //  base64 测试
        // 这里我单纯的模拟，用 Base64 加解密，一般都是通过对称加密或非对称加密实现
        // 这里简化了，我直接转换了，一般需要做判断，我的实现是定义一个接口，接口有定义一个获取加密字段的方法。
        Object[] args = joinPoint.getArgs();
        log.info("请求入参 {}",JSON.toJSONString(args));
        try {
            Person person = (Person)args[0];
            String decryptString = new String(Base64.getDecoder().decode(person.getEncryptData().getBytes(StandardCharsets.UTF_8)),"UTF-8");
            JSONObject jsonObject = JSON.parseObject(decryptString);
            // 这里简化了，可以用反射。
            person.setName(String.valueOf(jsonObject.get("name")));
            person.setAddress(String.valueOf(jsonObject.get("address")));
            person.setAge(Integer.valueOf(String.valueOf(jsonObject.get("age"))));
        }
        catch (Exception e){
            log.error("解密出错 {}",e.getMessage(),e);
        }
    }
}
