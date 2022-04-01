package com.yichen.decryptdemo.filter;

import com.yichen.decryptdemo.servlet.DecryptServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/31 22:28
 * @describe
 */
@Slf4j
public class DecryptFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 为什么使用自定义类包装 request,因为 request 一旦读取后，无法在此读取，也不能添加入参，这里包装后重写 getInputStream() 方法即可克服以上功能
        DecryptServletRequestWrapper requestWrapper = null;
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            requestWrapper = new DecryptServletRequestWrapper(httpServletRequest);
        }
        catch (Exception e){
            log.error("DecryptServletRequestWrapper error {}",e.getMessage(),e);
        }
        chain.doFilter((Objects.isNull(requestWrapper) ? request : requestWrapper),response);
    }
}
