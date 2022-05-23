package com.cy.store.config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lizhenghao
 * @create 2022-02-18-12:13
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    HandlerInterceptor interceptor = new LoginInterceptor();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/bootstrap3/**","/css/**","/images/**","/js/**",
                        "/web/register.html","/web/login.html","index.html","/web/product.html",
                        "/users/reg","/users/login","/districts/**","/web/index.html","/products/**");
    }
}
