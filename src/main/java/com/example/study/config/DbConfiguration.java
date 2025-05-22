package com.example.study.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.*;

// 밑과 같이 configuration 을 조각조각 구성 할 수도 있음
@Configuration
@PropertySource("classpath:dbConfig.properties") // dbConfig.properties 파일을 불러옴, y(a)ml 파일은 안됨
public class DbConfiguration {
    @Autowired
    private Environment env;

    @Override
    public String toString() {
        return "Username: " + env.getProperty("user") +
                ", Password: " + env.getProperty("password");
    }
}
