package com.hhu.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class FilePath implements WebMvcConfigurer {
    private String filePath = "static/poster";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/poster/**").addResourceLocations("classpath:/"+filePath+"/");
    }
}
