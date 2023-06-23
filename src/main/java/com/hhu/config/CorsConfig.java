package com.hhu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
            .allowedHeaders("*") // 允许的请求头
            .allowCredentials(true) // 是否允许发送Cookie
            .maxAge(3600); // 预检请求的有效期，单位为秒
    }
}