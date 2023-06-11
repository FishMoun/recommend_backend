package com.hhu;

import com.hhu.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RecommendBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecommendBackendApplication.class, args);
    }

}
