package com.yichen.decryptdemo.config;

import com.yichen.decryptdemo.filter.DecryptFilter;
import com.yichen.decryptdemo.interceptor.DecryptInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/30 21:46
 * @describe 解密配置
 *     参考解决办法  https://yaoyuanyy.github.io/2019/09/21/springboot%20HandlerIntercepter%E6%8B%A6%E6%88%AA%E5%99%A8%E5%AE%9E%E7%8E%B0%E4%BF%AE%E6%94%B9request%20body%E6%95%B0%E6%8D%AE/
 */
@Configuration
public class DecryptConfiguration implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        DecryptInterceptor interceptor = new DecryptInterceptor();
        registry.addInterceptor(interceptor);
    }

    @Bean
    public FilterRegistrationBean<?> decryptDataRegistrationBean(){
        DecryptFilter filter = new DecryptFilter();
        FilterRegistrationBean<DecryptFilter> bean = new FilterRegistrationBean<>();
        bean.setName("decrypt-data");
        bean.setFilter(filter);
        bean.addUrlPatterns("/*");
        bean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return bean;
    }
}
