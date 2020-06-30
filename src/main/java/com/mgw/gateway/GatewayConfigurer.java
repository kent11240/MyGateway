package com.mgw.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mgw.interceptor.GatewayInterceptor;

@Configuration
@EnableWebMvc
@PropertySource(value = {"classpath:mygateway.properties"}, encoding = "utf-8")
public class GatewayConfigurer implements WebMvcConfigurer {
	@Autowired
    private GatewayInterceptor gatewayInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(gatewayInterceptor).addPathPatterns("/**");
    }
}
