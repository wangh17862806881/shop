package com.fh.shop.api.WebConfig;

import com.fh.shop.api.argumentResolver.ArgumentResolver;
import com.fh.shop.api.interceptor.IdempotentInterceptor;
import com.fh.shop.api.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
//配置拦截器  加载
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
       //配置登陆拦截器
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
        //配置幂等性  解决重复提交
        registry.addInterceptor(idempotentInterceptor()).addPathPatterns("/**");
       }
//配置 自定义解析器
    public void addArgumentResolvers(java.util.List<org.springframework.web.method.support.HandlerMethodArgumentResolver> argumentResolvers) {

        //配置 自定义参数解析器
        argumentResolvers.add(argumentResolver());

        }

        @Bean
        public ArgumentResolver argumentResolver(){
            return new ArgumentResolver();
        }

         @Bean
       public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
       }
        @Bean
       public IdempotentInterceptor idempotentInterceptor(){
        return new IdempotentInterceptor();
       }

}
