package com.yichen.decryptdemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/1 22:26
 * @describe 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * validate 校验结果
     *   这里可以打 debug 观察为什么 @Valid 先于自定义 Aspect 触发
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String errorHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getFieldErrors().get(0).getDefaultMessage();

        log.warn("[MethodArgumentNotValidException参数验证异常] url:{},message:{}", request.getRequestURI(), message);
        return message;
    }

    /**
     * 这里用于观测  未包装的 httpServletRequest 只能读取一次，且无法重置流。
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e, HttpServletRequest request){
        log.error("捕获 Exception {}",e.getMessage(),e);
        return e.getMessage();
    }
}
